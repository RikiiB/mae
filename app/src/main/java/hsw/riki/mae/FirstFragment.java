package hsw.riki.mae;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.snackbar.Snackbar;
import hsw.riki.mae.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

  final String PERMISSION = Manifest.permission.READ_PHONE_NUMBERS;

  private FragmentFirstBinding binding;

  private final ActivityResultLauncher<String> requestPermissionLauncher =
      registerForActivityResult(
          new ActivityResultContracts.RequestPermission(), this::evaluatePermRequestResult);

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentFirstBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.saveButton.setOnClickListener(
        ev -> {
          FragmentTransaction ta = requireActivity().getSupportFragmentManager().beginTransaction();
          ta.add(
              R.id.listLinearLayout,
              ContactListFragment.newInstance(binding.nameTextField.getText().toString()));
          ta.addToBackStack(null);
          ta.commit();

          //            TextView tv = new TextView(getActivity());
          //            tv.setText(binding.nameTextField.getText());
          //            binding.listLinearLayout.addView(tv);

          Intent contactsContract = new Intent(ContactsContract.Intents.Insert.ACTION);
          contactsContract.setType(ContactsContract.RawContacts.CONTENT_TYPE);
          contactsContract.putExtra(
              ContactsContract.Intents.Insert.NAME, binding.nameTextField.getText().toString());
          contactsContract.putExtra(
              ContactsContract.Intents.Insert.PHONE, binding.phoneTextField.getText());
          contactsContract.putExtra("finishActivityOnSaveCompleted", true);
          startActivity(contactsContract);
        });

    binding.dialButton.setOnClickListener(
        v -> {
          Intent dialer = new Intent(Intent.ACTION_DIAL);
          dialer.setData(Uri.parse("tel:" + binding.phoneTextField.getText()));
          startActivity(dialer);
        });

    binding.readPhoneNumberPermissionButton.setOnClickListener(
        v -> {
          if (this.requireActivity().checkSelfPermission(PERMISSION)
              == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(
                    requireActivity(), R.string.permission_already_granted, Toast.LENGTH_LONG)
                .show();
          } else if (this.requireActivity().shouldShowRequestPermissionRationale(PERMISSION)) {
            Snackbar.make(requireView(), R.string.permission_request, Snackbar.LENGTH_LONG)
                .setAction(
                    R.string.permission, action -> requestPermissionLauncher.launch(PERMISSION))
                .show();
          } else {
            requestPermissionLauncher.launch(PERMISSION);
          }
        });

    binding.nextButton.setOnClickListener(
        v ->
            NavHostFragment.findNavController(FirstFragment.this)
                .navigate(FirstFragmentDirections.actionFirstFragmentToNameInputFragment()));

    binding.viewsButton.setOnClickListener(
        v ->
            NavHostFragment.findNavController(FirstFragment.this)
                .navigate(FirstFragmentDirections.actionFirstFragmentToViewsFragment()));

    binding.listButton.setOnClickListener(
        v ->
            NavHostFragment.findNavController(FirstFragment.this)
                .navigate(FirstFragmentDirections.actionFirstFragmentToMonthListFragment()));

    binding.toNotificationbutton.setOnClickListener(
        v -> {
          NavHostFragment.findNavController(FirstFragment.this)
              .navigate(FirstFragmentDirections.actionFirstFragmentToNotificationsFragment());
        });

    binding.uhrzeitButton.setOnClickListener(
        v -> {
          NavHostFragment.findNavController(FirstFragment.this)
              .navigate(FirstFragmentDirections.actionFirstFragmentToThreadFragment());
        });

    binding.datenspeicherButton.setOnClickListener(
        v -> {
          NavHostFragment.findNavController(FirstFragment.this)
              .navigate(FirstFragmentDirections.actionFirstFragmentToDatenspeicherFragment());
        });

    if (requireActivity().getIntent().getExtras() != null
        && requireActivity().getIntent().getExtras().containsKey("hsw.riki.mae.msg")) {
      Toast.makeText(
              requireContext(),
              requireActivity().getIntent().getStringExtra("hsw.riki.mae.msg"),
              Toast.LENGTH_LONG)
          .show();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  private void evaluatePermRequestResult(Boolean granted) {
    if (granted) {
      Toast.makeText(requireActivity(), R.string.permission_granted, Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(requireActivity(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
    }
  }
}
