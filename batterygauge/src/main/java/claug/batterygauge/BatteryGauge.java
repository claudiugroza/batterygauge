package claug.batterygauge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import claug.batterygauge.core.BatteryFuelGaugeMeter;

public class BatteryGauge {
  private static final String TAG = BatteryGauge.class.getSimpleName();

  @SuppressLint("StaticFieldLeak")
  private static BatteryFuelGaugeMeter meter;

  public static void init(Context context) {
    meter = new BatteryFuelGaugeMeter(context, MeasurementConfiguration.DEFAULT);
  }

  public static void destroy() {
    meter = null;
  }

  public static void logStats() {
      String log = meter.buildLog();
      Log.i(TAG, log);
  }

}
