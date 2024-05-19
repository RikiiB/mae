package hsw.riki.mae.util.oberserver;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import hsw.riki.mae.viewModel.ViewsViewModel;
import java.util.Timer;
import java.util.TimerTask;

public class ViewsLifecycleObserver implements DefaultLifecycleObserver {

  private ViewsViewModel viewModel;
  private Timer timer;
  private TimerTask timerTask;

  public ViewsLifecycleObserver(ViewsViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @Override
  public void onResume(@NonNull LifecycleOwner owner) {
    timer = new Timer();
    viewModel
        .getIsChecked()
        .observeForever(
            r -> {
              if (Boolean.TRUE.equals(r)) start();
              else stop();
            });
  }

  public void start() {
    /*timerTask = (TimerTask) -> {
        Integer value = viewModel.getIsChecked().getValue();
        if (null == value) viewModel.getIsChecked().postValue(0);
        else viewModel.getIsChecked().postValue(value + 1);
    }*/
  }

  public void stop() {}
}
