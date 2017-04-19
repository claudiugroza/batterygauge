package claug.batterygauge.logging;

import android.util.Log;
import claug.batterygauge.datasource.FuelGaugeDataSource;
import claug.batterygauge.domain.usecase.ReadBatteryGaugeUseCase;

public class BatteryGauge {

  private static final String TAG = BatteryGauge.class.getSimpleName();

  private String tag = BatteryGauge.class.getSimpleName();
  private final boolean filterNa;
  private final ReadBatteryGaugeUseCase readBatteryGaugeUseCase;

  private static BatteryGauge batteryGauge;

  private BatteryGauge(BatteryGaugeConfig config) {
    if (config.getTag() != null) {
      this.tag = config.getTag();
    } else {
      this.tag = TAG;
    }

    this.filterNa = config.isFilterNa();

    this.readBatteryGaugeUseCase = new ReadBatteryGaugeUseCase(
        new FuelGaugeDataSource(config.getContext()),
        config.buildMeasurementConfig());
  }

  public static void init(BatteryGaugeConfig config) {
    batteryGauge = new BatteryGauge(config);
  }

  public static void destroy() {
    batteryGauge = null;
  }

  public static void log() {
    if (batteryGauge == null) {
      Log.i(TAG, "BatteryGauge not configured.");
      return;
    }

    BatteryPropertyLogger logger = new BatteryPropertyLogger(
        batteryGauge.readBatteryGaugeUseCase.readAllProperties(),
        batteryGauge.filterNa);
    Log.i(batteryGauge.tag, logger.computeLog());
  }

}
