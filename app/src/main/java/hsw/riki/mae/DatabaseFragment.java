package hsw.riki.mae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hsw.riki.mae.adapter.ContactCursorAdapter;
import hsw.riki.mae.databinding.FragmentDatabaseBinding;
import hsw.riki.mae.util.helper.ContactOpenHelper;

public class DatabaseFragment extends Fragment {
  private FragmentDatabaseBinding binding;
  private ContactOpenHelper helper;
  private ContactCursorAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentDatabaseBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new ContactCursorAdapter(requireContext());
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    helper = new ContactOpenHelper(requireContext());

    binding.saveDbEntryButton.setOnClickListener(
        v -> {
          String firstname = binding.firstnameInput.getText().toString();
          String lastname = binding.lastnameInput.getText().toString();
          insert(firstname, lastname);
        });
    load();
  }

  private void load() {
    int sum = helper.count();
    //    int sum = values.values().stream().mapToInt(v -> v).sum();

    binding.numberOfEntrysVar.setText(Integer.toString(sum));
  }

  private void insert(String firstname, String lastname) {
    helper.insert(firstname, lastname);
    load();
  }

  private void showNamesFromDb() {}
}
