package hsw.riki.mae.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewsViewModel extends ViewModel {
  private final MutableLiveData<Boolean> isChecked;

  public ViewsViewModel() {
    isChecked = new MutableLiveData<>(false);
  }

  public void reset() {
    isChecked.setValue(false);
  }

  public MutableLiveData<Boolean> getIsChecked() {
    return isChecked;
  }
}
