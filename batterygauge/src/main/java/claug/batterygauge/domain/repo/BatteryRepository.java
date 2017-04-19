package claug.batterygauge.domain.repo;

import claug.batterygauge.domain.model.BatteryProperty;
import java.util.List;

public interface BatteryRepository {

  BatteryProperty readVoltage();

  BatteryProperty readTemperature();

  List<BatteryProperty> readFuelGaugeProperties();

  List<BatteryProperty> readAll();

}
