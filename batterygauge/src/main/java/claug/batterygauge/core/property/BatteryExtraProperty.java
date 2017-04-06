package claug.batterygauge.core.property;

import android.os.BatteryManager;

public enum BatteryExtraProperty implements Property {
  voltage("Voltage", BatteryManager.EXTRA_VOLTAGE, "mV"),
  temperature("Temperature", BatteryManager.EXTRA_TEMPERATURE, "°C");

  private final String literal;
  private final String property;
  private final String units;

  BatteryExtraProperty(String literal, String property, String units) {
    this.literal = literal;
    this.property = property;
    this.units = units;
  }

  @Override
  public String getLiteral() {
    return literal;
  }

  public String getProperty() {
    return property;
  }

  @Override
  public String getUnits() {
    return units;
  }
}
