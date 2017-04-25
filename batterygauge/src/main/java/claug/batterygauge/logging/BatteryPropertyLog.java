package claug.batterygauge.logging;

import claug.batterygauge.domain.Constants;
import claug.batterygauge.domain.model.BatteryProperty;

class BatteryPropertyLog {

  private final BatteryProperty batteryProperty;

  BatteryPropertyLog(BatteryProperty batteryProperty) {
    this.batteryProperty = batteryProperty;
  }

  boolean isNa() {
    return batteryProperty.getValue() == Constants.NA_PLACEHOLDER;
  }

  String composeLog() {
    StringBuilder builder = new StringBuilder();
    builder.append(batteryProperty.getName());
    builder.append(" = ");

    float value = batteryProperty.getValue();
    if (value != Constants.NA_PLACEHOLDER) {
      if (value % 1 == 0) {
        builder.append((int) value);
      } else {
        builder.append(value);
      }
      builder.append(" ").append(batteryProperty.getUnits());
    } else {
      builder.append("NA");
    }

    return builder.toString();
  }

}
