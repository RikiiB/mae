package hsw.riki.mae.adapter;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hsw.riki.mae.R;
import hsw.riki.mae.databinding.FragmentSensorBinding;

public class SensorFragment extends Fragment {

  private FragmentSensorBinding binding;
  private SensorManager sensorManager;
  private SensorEventListener sensorEventListener;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentSensorBinding.inflate(inflater, container, false);
    sensorManager = requireActivity().getSystemService(SensorManager.class);
    sensorEventListener =
        new SensorEventListener() {
          @SuppressLint("StringFormatInvalid")
          @Override
          public void onSensorChanged(SensorEvent event) {
            binding.constantValue.setText("Constant: " +
                getString(R.string.sensor_lightsensor_data, event.values[0]));
            binding.sensorValue.setText("Sensor value: " + String.valueOf(event.values[0]));
            int constance = R.string.sensor_lightsensor_data;
            float difference = 600 - event.values[0];
            binding.difference.setText("Difference " + String.valueOf(difference));
          }

          @Override
          public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.d(getClass().getSimpleName(), "Accuracy changed (" + sensor + "):" + accuracy);
          }
        };
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
    sensorManager.unregisterListener(sensorEventListener);
  }
}
