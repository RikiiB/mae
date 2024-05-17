package hsw.riki.mae;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import hsw.riki.mae.adapter.MonthBaseAdapter;

public class MonthListFragment extends ListFragment {
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setListAdapter(new MonthBaseAdapter(getContext()));
  }
}
