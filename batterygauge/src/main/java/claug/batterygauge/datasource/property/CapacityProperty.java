package claug.batterygauge.datasource.property;

import static claug.batterygauge.domain.Util.adjust;

import android.os.BatteryManager;
import claug.batterygauge.domain.model.BatteryProperty;

public class CapacityProperty implements BatteryProperty {

  private final float value;

  public CapacityProperty(BatteryManager bm) {
    this.value = adjust(bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY), 0);
  }

  @Override
  public float getValue() {
    return value;
  }

  @Override
  public String getName() {
    return "Capacity";
  }

  @Override
  public String getUnits() {
    return "%";
  }

}