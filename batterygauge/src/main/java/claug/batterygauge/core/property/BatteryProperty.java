package claug.batterygauge.core.property;

import android.os.BatteryManager;

public enum BatteryProperty implements Property {
  CHARGE_COUNTER("ChargeCounter", BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER, "uAH"),
  CURRENT_NOW("CurrentNow", BatteryManager.BATTERY_PROPERTY_CURRENT_NOW, "uA"),
  CURRENT_AVERAGE("ChargeCounter", BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE, "uA"),
  CAPACITY("Capacity", BatteryManager.BATTERY_PROPERTY_CAPACITY, "%"),
  ENERGY_COUNTER("EnergyCounter", BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER, "uWH");

  private final String literal;
  private final int property;
  private final String units;

  BatteryProperty(String literal, int property, String units) {
    this.literal = literal;
    this.property = property;
    this.units = units;
  }

  @Override
  public String getLiteral() {
    return literal;
  }

  public int getProperty() {
    return property;
  }

  @Override
  public String getUnits() {
    return units;
  }

}
