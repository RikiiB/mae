package hsw.riki.mae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import hsw.riki.mae.databinding.FragmentNameOutputBinding;
import hsw.riki.mae.enumTypes.Gender;

public class NameOutputFragment extends Fragment {
  private FragmentNameOutputBinding binding;

  private String firstName;
  private String lastName;
  private String gender;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentNameOutputBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    NameOutputFragmentArgs args = NameOutputFragmentArgs.fromBundle(getArguments());
    this.firstName = args.getFirstName();
    this.lastName = args.getLastName();
    this.gender = args.getGender();

    String anrede = "";
    if (gender.equals(Gender.MALE.name())) {
      anrede = "Mr.";
    }
    if (gender.equals(Gender.FEMALE.name())) {
      anrede = "Mz.";
    }
    if (gender.equals(Gender.DIVERSE.name())) {
      anrede = "";
    }

    binding.textviewSecond.setText(
        String.format("Hello %s %s %s!", anrede, firstName, lastName).toString());

    /*binding.button.setOnClickListener(
    v ->
        NavHostFragment.findNavController(SecondFragment.this)
            .navigate(R.id.action_SecondFragment_to_FirstFragment));*/
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
