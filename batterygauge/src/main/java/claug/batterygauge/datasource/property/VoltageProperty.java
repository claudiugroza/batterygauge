package claug.batterygauge.datasource.property;

import android.content.Intent;
import android.os.BatteryManager;
import claug.batterygauge.domain.model.BatteryProperty;

public class VoltageProperty implements BatteryProperty {

  private final int value;

  public VoltageProperty(Intent intent) {
    this.value = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
  }

  @Override
  public float getValue() {
    return value;
  }

  @Override
  public String getName() {
    return "Voltage";
  }

  @Override
  public String getUnits() {
    return "mV";
  }

}
