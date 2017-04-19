package claug.batterygauge.logging;

import claug.batterygauge.domain.model.BatteryProperty;
import java.util.List;

class BatteryPropertyLogger {

  private final List<BatteryProperty> batteryProperties;
  private final boolean filterNa;

  BatteryPropertyLogger(List<BatteryProperty> batteryProperties, boolean filterNa) {
    this.batteryProperties = batteryProperties;
    this.filterNa = filterNa;
  }

  String computeLog() {

    StringBuilder builder = new StringBuilder();
    builder.append("{ ");

    for (int i = 0; i < batteryProperties.size(); i++) {
      BatteryProperty property = batteryProperties.get(i);
      BatteryPropertyLog propertyLog = new BatteryPropertyLog(property);

      if (filterNa && propertyLog.isNa()) {
        continue;
      }

      if (i != 0) {
        builder.append(", ");
      }

      builder.append(propertyLog.composeLog());

    }

    builder.append(" }");

    return builder.toString();
  }

}
