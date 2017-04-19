package claug.batterygauge.datasource.property;


import static claug.batterygauge.domain.Util.adjust;

import android.os.BatteryManager;
import claug.batterygauge.domain.model.BatteryProperty;

public class ChargeCounterProperty implements BatteryProperty {

  private final float value;

  public ChargeCounterProperty(BatteryManager bm) {
    this.value = adjust(bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER), 0);
  }

  @Override
  public float getValue() {
    return value;
  }

  @Override
  public String getName() {
    return "ChargeCounter";
  }

  @Override
  public String getUnits() {
    return "uAH";
  }

}
