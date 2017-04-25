package claug.batterygauge.datasource.property;


import android.content.Intent;
import android.os.BatteryManager;
import claug.batterygauge.domain.model.BatteryProperty;

public class TemperatureProperty implements BatteryProperty {

  private final float value;

  public TemperatureProperty(Intent intent) {
    this.value = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;
  }

  @Override
  public float getValue() {
    return value;
  }

  @Override
  public String getName() {
    return "Temperature";
  }

  @Override
  public String getUnits() {
    return "C";
  }

}
