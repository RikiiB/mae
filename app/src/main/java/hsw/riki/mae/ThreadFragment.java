package hsw.riki.mae;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hsw.riki.mae.databinding.FragmentThreadBinding;
import hsw.riki.mae.util.Weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreadFragment extends Fragment {
  private FragmentThreadBinding binding;
  private volatile boolean running;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentThreadBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding.buttonUhrzeitAn.setOnClickListener(
        v -> {
          if (!running) {
            running = true;
            new Thread(this::timeActivity).start();
          }
        });

    binding.buttonUhrzeitAus.setOnClickListener(
        v -> {
          running = false;
        });

    binding.multitaskingButtonRefresh.setOnClickListener(
        v -> new Thread(this::refreshWeather).start());
  }

  Handler handler = new Handler(Looper.getMainLooper());

  private void timeActivity() {
    while (running) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      String formattedTime = now.format(formatter);
      handler.post(() -> binding.textViewUhrzeitThread.setText(formattedTime));
      //      binding.textViewUhrzeitThread.setText(LocalTime.now().toString());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void refreshWeather() {
    String url =
        "https://api.openweathermap.org/data/2.5/weather?q="
            + binding.locationEditText.getText()
            + "&appid="
            + getString(R.string.api_key);

    try {
      HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
      if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
        requireActivity().runOnUiThread(() -> binding.weatherLayout.setVisibility(View.INVISIBLE));
        return;
      }

      String response =
          new BufferedReader(new InputStreamReader(connection.getInputStream()))
              .lines()
              .collect(Collectors.joining("\n"));
      JSONObject json = new JSONObject(response);
      connection.disconnect();

      Weather currentWeather = new Weather();
      currentWeather.setCity(json.getString("name"));

      if (json.has("main")) {
        JSONObject main = json.getJSONObject("main");
        currentWeather.setTemp(Double.toString(main.getDouble("temp") - 273.15));
      }

      if (json.has("weather")) {
        JSONArray weatherArray = json.getJSONArray("weather");
        if (weatherArray.length() > 0) {
          JSONObject weather = weatherArray.getJSONObject(0);
          currentWeather.setIcon(retrieveWeatherImage(weather.getString("icon")));
        }
      }

      requireActivity()
          .runOnUiThread(
              () -> {
                binding.weatherLayout.setVisibility(View.VISIBLE);
                binding.multitaskingWeatherCity.setText(currentWeather.getCity());
                binding.multitaskingWeatherTemp.setText(currentWeather.getTemp() + "°C");
                binding.multitaskingWeatherImg.setImageBitmap(currentWeather.getIcon());
              });

    } catch (IOException | JSONException e) {
      throw new RuntimeException(e);
    }
  }

  private Bitmap retrieveWeatherImage(String imageName) {
    try {
      HttpURLConnection con =
          (HttpURLConnection)
              new URL("https://openweathermap.org/img/w/" + imageName + ".png").openConnection();
      Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
      con.disconnect();
      return bitmap;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
