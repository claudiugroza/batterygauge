package claug.batterygauge.domain;

public class MeasurementConfig {

  private final int samples;
  private final SamplingMethod samplingMethod;

  public MeasurementConfig(int samples, SamplingMethod samplingMethod) {

    this.samples = samples;
    this.samplingMethod = samplingMethod;
  }

  public int getSamples() {
    return samples;
  }

  public SamplingMethod getSamplingMethod() {
    return samplingMethod;
  }

}
