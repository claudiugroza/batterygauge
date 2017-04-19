package claug.batterygauge.datasource;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import claug.batterygauge.datasource.property.CapacityProperty;
import claug.batterygauge.datasource.property.ChargeCounterProperty;
import claug.batterygauge.datasource.property.CurrentAverageProperty;
import claug.batterygauge.datasource.property.CurrentNowProperty;
import claug.batterygauge.datasource.property.EnergyCounterProperty;
import claug.batterygauge.datasource.property.TemperatureProperty;
import claug.batterygauge.datasource.property.VoltageProperty;
import claug.batterygauge.domain.model.BatteryProperty;
import claug.batterygauge.domain.repo.BatteryRepository;
import java.util.LinkedList;
import java.util.List;

public class FuelGaugeDataSource implements BatteryRepository {

  private final Context context;

  public FuelGaugeDataSource(Context context) {
    this.context = context;
  }

  @Override
  public BatteryProperty readVoltage() {
    return new VoltageProperty(readExtraIntent(context));
  }

  @Override
  public BatteryProperty readTemperature() {
    return new TemperatureProperty(readExtraIntent(context));
  }

  @Override
  public List<BatteryProperty> readFuelGaugeProperties() {
    List<BatteryProperty> properties = new LinkedList<>();

    BatteryManager batteryManager =
        (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);

    properties.add(new CapacityProperty(batteryManager));
    properties.add(new CurrentNowProperty(batteryManager));
    properties.add(new CurrentAverageProperty(batteryManager));
    properties.add(new ChargeCounterProperty(batteryManager));
    properties.add(new EnergyCounterProperty(batteryManager));

    return properties;
  }

  @Override
  public List<BatteryProperty> readAll() {
    List<BatteryProperty> properties = new LinkedList<>();

    Intent intent = readExtraIntent(context);
    properties.add(new VoltageProperty(intent));
    properties.add(new TemperatureProperty(intent));

    properties.addAll(readFuelGaugeProperties());

    return properties;
  }

  private static Intent readExtraIntent(Context context) {
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    return context.registerReceiver(null, filter);
  }

}
