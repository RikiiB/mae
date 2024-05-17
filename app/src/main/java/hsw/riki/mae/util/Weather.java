package hsw.riki.mae.util;

import android.graphics.Bitmap;

public class Weather {
  private String city;
  private String temp;
  private Bitmap icon;

  public Weather() {}

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getTemp() {
    return temp;
  }

  public void setTemp(String temp) {
    this.temp = temp;
  }

  public Bitmap getIcon() {
    return icon;
  }

  public void setIcon(Bitmap icon) {
    this.icon = icon;
  }
}
