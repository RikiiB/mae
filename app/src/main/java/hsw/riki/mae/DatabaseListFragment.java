package hsw.riki.mae;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import hsw.riki.mae.adapter.ContactCursorAdapter;
import hsw.riki.mae.util.helper.ContactOpenHelper;

public class DatabaseListFragment extends ListFragment {
  private ContactCursorAdapter adapter;
  private ContactOpenHelper helper;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new ContactCursorAdapter(requireContext());
    helper = new ContactOpenHelper(requireContext());
    setListAdapter(adapter);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter.changeCursor(helper.selectCursor());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    helper.close();
    helper = null;
  }
}
