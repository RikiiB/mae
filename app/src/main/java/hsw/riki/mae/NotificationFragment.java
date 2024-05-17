package hsw.riki.mae;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;
import hsw.riki.mae.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {

  private FragmentNotificationBinding binding;
  private final String POST_NOTIFICATIONS = Manifest.permission.POST_NOTIFICATIONS;
  private final ActivityResultLauncher<String> requestPermissionLauncher =
      registerForActivityResult(
          new ActivityResultContracts.RequestPermission(), this::evaluatePermRequestResult);
  private NotificationManagerCompat manager;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    manager = NotificationManagerCompat.from(requireContext());
    manager.createNotificationChannel(
        new NotificationChannel(
            "mae-1", "Mobile AE Notification", NotificationManager.IMPORTANCE_DEFAULT));
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentNotificationBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.createSbButton.setOnClickListener(
        v -> {
          if (this.requireActivity().checkSelfPermission(POST_NOTIFICATIONS)
              == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(
                    this.requireView(),
                    //                    "Soll die Berechtigung wirklich angefordert werden?",
                    "Soll die Nachricht wirklich geschickt werden?",
                    Snackbar.LENGTH_LONG)
                .setAction(
                    //                    R.string.permission, v1 ->
                    // requestPermissionLauncher.launch(POST_NOTIFICATIONS))
                    "Create Notification", v1 -> sendNotification())
                .show();
          } else {
            requestPermissionLauncher.launch(POST_NOTIFICATIONS);
          }
        });
  }

  private void sendNotification() {
    Notification notification =
        new NotificationCompat.Builder(requireContext(), "mae-1")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentIntent(
                PendingIntent.getActivity(
                    requireContext(),
                    0,
                    new Intent(requireContext(), MainActivity.class)
                        .putExtra(
                            "hsw.riki.mae.msg", binding.notificationTextInput.getText().toString()),
                    PendingIntent.FLAG_UPDATE_CURRENT + PendingIntent.FLAG_IMMUTABLE))
            .setContentTitle(binding.notificationTitleInput.getText())
            .setContentText(binding.notificationTextInput.getText())
            .build();
    if (PackageManager.PERMISSION_GRANTED
        != requireActivity().checkSelfPermission(POST_NOTIFICATIONS)) {
      requestPermissionLauncher.launch(POST_NOTIFICATIONS);
    } else {
      manager.notify(123, notification);
    }
  }

  private void evaluatePermRequestResult(Boolean granted) {
    if (granted) {
      Toast.makeText(requireActivity(), R.string.permission_granted, Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(requireActivity(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
    }
  }
}
