package claug.batterygauge.datasource.property;

import static claug.batterygauge.domain.Util.adjust;

import android.os.BatteryManager;
import claug.batterygauge.domain.model.BatteryProperty;

public class EnergyCounterProperty implements BatteryProperty {

  private final float value;

  public EnergyCounterProperty(BatteryManager bm) {
    this.value =
        adjust(bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER), Long.MIN_VALUE);
  }

  @Override
  public float getValue() {
    return value;
  }

  @Override
  public String getName() {
    return "EnergyCounter";
  }

  @Override
  public String getUnits() {
    return "uWH";
  }

}