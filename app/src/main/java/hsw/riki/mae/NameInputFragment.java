package hsw.riki.mae;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import hsw.riki.mae.databinding.FragmentNameInputBinding;
import hsw.riki.mae.enumTypes.Gender;

public class NameInputFragment extends Fragment {
  private FragmentNameInputBinding binding;
  private String firstName;
  private String lastName;
  private String gender;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentNameInputBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.buttonMale.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (textFieldsAreNotEmpty()) {
              showToastEnterFullName();
            } else {
              setFullName();
              gender = Gender.MALE.name();
              moveToSecondFragment();
            }
          }
        });

    binding.buttonFemale.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (textFieldsAreNotEmpty()) {
              showToastEnterFullName();
            } else {
              setFullName();
              gender = Gender.FEMALE.name();
              moveToSecondFragment();
            }
          }
        });

    binding.buttonDiverse.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (textFieldsAreNotEmpty()) {
              showToastEnterFullName();
            } else {
              setFullName();
              gender = Gender.DIVERSE.name();
              moveToSecondFragment();
            }
          }
        });

    /*binding.buttonMale.setOnClickListener(v ->
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment)
    );
    binding.buttonFemale.setOnClickListener(v ->
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment)
    );
    binding.buttonDiverse.setOnClickListener(v ->
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment)
    );*/
  }

  private void setFullName() {
    firstName = binding.firstNameEditText.getText().toString();
    lastName = binding.lastNameEditText.getText().toString();
  }

  private void showToastEnterFullName() {
    Toast.makeText(getContext(), "Please enter your full name.", Toast.LENGTH_SHORT).show();
  }

  private boolean textFieldsAreNotEmpty() {
    return TextUtils.isEmpty(binding.firstNameEditText.getText())
        || TextUtils.isEmpty(binding.lastNameEditText.getText());
  }

  private void moveToSecondFragment() {
    /*NavHostFragment.findNavController(FirstFragment.this)
    .navigate(R.id.action_FirstFragment_to_SecondFragment);*/
    NavHostFragment.findNavController(NameInputFragment.this)
        .navigate(
            NameInputFragmentDirections.actionNameInputFragmentToNameOutputFragment(
                //                this.gender, this.firstName, this.lastName)); // TODO: welche
                // Reihenfolge hier?
                this.firstName, this.lastName, this.gender));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
