package claug.batterygauge.domain;

public class Util {

  public static float adjust(int value, int naValue) {
    if (value == naValue) {
      return Constants.NA_PLACEHOLDER;
    }
    return value;
  }

  public static float adjust(long value, long naValue) {
    if (value == naValue) {
      return Constants.NA_PLACEHOLDER;
    }
    return value;
  }

}
