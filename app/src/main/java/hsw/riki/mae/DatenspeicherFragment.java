package hsw.riki.mae;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hsw.riki.mae.databinding.FragmentDatenspeicherBinding;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class DatenspeicherFragment extends Fragment {
  private FragmentDatenspeicherBinding binding;
  private String directory;
  private String filename;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentDatenspeicherBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.saveStorageButton.setOnClickListener(
        v -> {
          directory = binding.ordnernameInput.getText().toString();
          filename = binding.dateinameInput.getText().toString();
          try (OutputStreamWriter writer =
              new OutputStreamWriter(
                  Files.newOutputStream(
                      new File(requireActivity().getDir(directory, Context.MODE_PRIVATE), filename)
                          .toPath()))) {
            writer.write(binding.dateiinhaltInput.getText().toString());
          } catch (IOException e) {
            Toast.makeText(requireContext(), R.string.storage_filestorage_error, Toast.LENGTH_LONG)
                .show();
          }
        });

    binding.loadStorageButton.setOnClickListener(
        v -> {
          directory = binding.ordnernameInput.getText().toString();
          filename = binding.dateinameInput.getText().toString();
          try (BufferedReader reader =
              new BufferedReader(
                  new InputStreamReader(
                      Files.newInputStream(
                          new File(
                                  requireActivity().getDir(directory, Context.MODE_PRIVATE),
                                  filename)
                              .toPath())))) {
            binding.dateiinhaltInput.setText(reader.lines().collect(Collectors.joining("\n")));
          } catch (IOException e) {
            Toast.makeText(requireContext(), R.string.storage_filestorage_error, Toast.LENGTH_LONG)
                .show();
          }
        });

    binding.deleteStorageButton.setOnClickListener(
        v -> {
          directory = binding.ordnernameInput.getText().toString();
          filename = binding.dateinameInput.getText().toString();
          File datei =
              new File(requireActivity().getDir(directory, Context.MODE_PRIVATE), filename);
          if (datei.delete()) {
            Toast.makeText(
                    requireContext(),
                    R.string.storage_filestorage_successfully_deleted,
                    Toast.LENGTH_LONG)
                .show();
          } else {
            Toast.makeText(requireContext(), R.string.storage_filestorage_error, Toast.LENGTH_LONG)
                .show();
          }
        });
  }
}
