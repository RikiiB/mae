package hsw.riki.mae.views;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import hsw.riki.mae.R;
import hsw.riki.mae.databinding.FragmentViewsBinding;
import hsw.riki.mae.viewModel.ViewsViewModel;

public class ViewsFragment extends Fragment {

  private FragmentViewsBinding binding;
  private ViewsViewModel viewModel;

  public ViewsFragment() {
    super(R.layout.fragment_views);
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    viewModel = new ViewModelProvider(this).get(ViewsViewModel.class);
    //    getLifecycle().addObserver();
    binding = FragmentViewsBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    registerForContextMenu(binding.cbStatus);

    /*binding.cbStatus.setOnCheckedChangeListener(
        (v, isChecked) -> {
          if (isChecked) {
            binding.cbStatusText.setText(R.string.cb_activated);
          } else {
            binding.cbStatusText.setText(R.string.cb_deactivated);
          }
        });

    binding.changeStatusButton.setOnClickListener(
        v -> {
          binding.cbStatus.setChecked(!binding.cbStatus.isChecked());
        });*/

    viewModel
        .getIsChecked()
        .observe(
            this.requireActivity(),
            r -> {
              if (r) {
                binding.cbStatusText.setText(R.string.cb_activated);
              } else {
                binding.cbStatusText.setText(R.string.cb_deactivated);
              }
            });

    registerForContextMenu(binding.cbStatus);

    binding.cbStatus.setOnCheckedChangeListener(
        (buttonView, isChecked) ->
            viewModel
                .getIsChecked()
                .setValue(Boolean.FALSE.equals(viewModel.getIsChecked().getValue())));

    binding.changeStatusButton.setOnClickListener(
        v -> {
          binding.cbStatus.setChecked(!binding.cbStatus.isChecked());
        });

    /*viewModel.getChangeStatus().observe(this.requireActivity(), d -> {
      if (d) {
        binding.changeStatusButton.
      }
    });*/
  }

  @Override
  public void onCreateContextMenu(
      @NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
  }

  @Override
  public boolean onContextItemSelected(@NonNull MenuItem item) {
    return super.onContextItemSelected(item);
  }
}
