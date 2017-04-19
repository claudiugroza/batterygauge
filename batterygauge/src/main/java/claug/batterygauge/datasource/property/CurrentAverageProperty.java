package claug.batterygauge.datasource.property;


import static claug.batterygauge.domain.Util.adjust;

import android.os.BatteryManager;
import claug.batterygauge.domain.model.BatteryProperty;

public class CurrentAverageProperty implements BatteryProperty {

  private final float value;

  public CurrentAverageProperty(BatteryManager bm) {
    this.value = adjust(bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE), 0);
  }

  @Override
  public float getValue() {
    return value;
  }

  @Override
  public String getName() {
    return "CurrentAverage";
  }

  @Override
  public String getUnits() {
    return "uA";
  }

}
