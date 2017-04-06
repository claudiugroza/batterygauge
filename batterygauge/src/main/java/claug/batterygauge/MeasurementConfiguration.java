package claug.batterygauge;

public class MeasurementConfiguration {

  private final int repeatedMeasurements;
  private final boolean filterEmptyProperties;
  private final SamplingMethod samplingMethod;

  public static MeasurementConfiguration DEFAULT =
      new MeasurementConfiguration(10, true, SamplingMethod.MEDIAN);

  public MeasurementConfiguration(
      int repeatedMeasurements,
      boolean filterEmptyProperties,
      SamplingMethod samplingMethod) {
    this.repeatedMeasurements = repeatedMeasurements;
    this.filterEmptyProperties = filterEmptyProperties;
    this.samplingMethod = samplingMethod;
  }

  public int getRepeatedMeasurements() {
    return repeatedMeasurements;
  }

  public boolean isFilterEmptyProperties() {
    return filterEmptyProperties;
  }

  public SamplingMethod getSamplingMethod() {
    return samplingMethod;
  }

}
