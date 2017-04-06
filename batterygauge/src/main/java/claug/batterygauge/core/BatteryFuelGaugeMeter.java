package claug.batterygauge.core;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import claug.batterygauge.MeasurementConfiguration;
import claug.batterygauge.core.property.BatteryExtraProperty;
import claug.batterygauge.core.property.BatteryProperty;
import claug.batterygauge.core.property.Property;
import java.util.HashMap;
import java.util.Map;

public class BatteryFuelGaugeMeter {
  private static final String TAG = BatteryFuelGaugeMeter.class.getSimpleName();

  private final Context context;

  private final MeasurementConfiguration configuration;

  private int measurementStep = 0;

  private static float NA_PLACEHOLDER = Float.MAX_VALUE;

  public BatteryFuelGaugeMeter(Context context, MeasurementConfiguration configuration) {
    this.context = context;
    this.configuration = configuration;
  }

  public String buildLog() {
    Log.d(TAG, "buildLog: ");

    StringBuilder builder = new StringBuilder();

    builder.append("Step ").append(measurementStep).append(" { ");

    int i = 0;
    Map<Property, Float> measurementStep = measure();
    for (Map.Entry<Property, Float> measurement : measurementStep.entrySet()) {
      builder.append(measurement.getKey()).append(": ");

      float value = measurement.getValue();
      if (configuration.isFilterEmptyProperties() && value != NA_PLACEHOLDER) {
        builder.append(value).append(measurement.getKey().getUnits());
      } else {
        builder.append("NA");
      }

      if (i < measurementStep.size() - 1) {
        builder.append(", ");
      }

      i++;
    }

    builder.append(" }");

    return builder.toString();
  }

  private Map<Property, Float> measure() {
    Log.d(TAG, "measure: ");

    measurementStep++;

    Map<Property, Float> stats = new HashMap<>();

    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    Intent bs = context.registerReceiver(null, filter);
    BatteryManager bm = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);

    stats.putAll(extractExtraValues(bs));
    stats.putAll(extractProperties(bm));

    return stats;
  }

  private static Map<Property, Float> extractExtraValues(Intent bs) {
    Map<Property, Float> extras = new HashMap<>();

    float voltage = bs.getIntExtra(BatteryExtraProperty.voltage.getProperty(), 0);
    float temperature =
        ((float) bs.getIntExtra(BatteryExtraProperty.temperature.getProperty(), 0)) / 10;

    extras.put(BatteryExtraProperty.voltage, voltage);
    extras.put(BatteryExtraProperty.temperature, temperature);

    return extras;
  }

  private static Map<Property, Float> extractProperties(BatteryManager bm) {
    Map<Property, Float> props = new HashMap<>();

    float chargeCounter = fix(bm.getIntProperty(BatteryProperty.CHARGE_COUNTER.getProperty()), 0);

    float currentAverage = fix(bm.getIntProperty(BatteryProperty.CURRENT_AVERAGE.getProperty()), 0);

    float currentNow = fix(bm.getIntProperty(BatteryProperty.CURRENT_NOW.getProperty()), 0);

    float capacity = fix(bm.getIntProperty(BatteryProperty.CAPACITY.getProperty()), 0);

    float energyCounter =
        fix(bm.getLongProperty(BatteryProperty.ENERGY_COUNTER.getProperty()), Long.MIN_VALUE);

    props.put(BatteryProperty.CHARGE_COUNTER, chargeCounter);
    props.put(BatteryProperty.CURRENT_AVERAGE, currentAverage);
    props.put(BatteryProperty.CURRENT_NOW, currentNow);
    props.put(BatteryProperty.CAPACITY, capacity);
    props.put(BatteryProperty.ENERGY_COUNTER, energyCounter);

    return props;
  }

  private static float fix(int value, int naValue) {
    if (value == naValue) {
      return NA_PLACEHOLDER;
    }
    return value;
  }

  private static float fix(long value, long naValue) {
    if (value == naValue) {
      return NA_PLACEHOLDER;
    }
    return value;
  }

}
