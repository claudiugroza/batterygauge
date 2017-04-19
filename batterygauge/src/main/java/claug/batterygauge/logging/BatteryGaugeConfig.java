package claug.batterygauge.logging;

import android.content.Context;
import claug.batterygauge.domain.MeasurementConfig;
import claug.batterygauge.domain.SamplingMethod;

public class BatteryGaugeConfig {

  private final Context context;
  private boolean filterNa = true;
  private String tag;

  public BatteryGaugeConfig(Context context) {
    this.context = context;
  }

  public BatteryGaugeConfig filterNa(boolean filterNa) {
    this.filterNa = filterNa;
    return this;
  }

  public BatteryGaugeConfig tag(String tag) {
    this.tag = tag;
    return this;
  }

  Context getContext() {
    return context;
  }

  MeasurementConfig buildMeasurementConfig() {
    return new MeasurementConfig(1, SamplingMethod.MEDIAN);
  }

  String getTag() {
    return tag;
  }

  public boolean isFilterNa() {
    return filterNa;
  }

}
