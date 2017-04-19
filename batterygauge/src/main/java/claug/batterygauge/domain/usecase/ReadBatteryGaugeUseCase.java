package claug.batterygauge.domain.usecase;


import claug.batterygauge.domain.MeasurementConfig;
import claug.batterygauge.domain.model.BatteryProperty;
import claug.batterygauge.domain.repo.BatteryRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class ReadBatteryGaugeUseCase {

  private final BatteryRepository repository;
  private final MeasurementConfig config;

  public ReadBatteryGaugeUseCase(BatteryRepository repository, MeasurementConfig config) {
    this.repository = repository;
    this.config = config;
  }

  public List<BatteryProperty> readAllProperties() {
    List<List<BatteryProperty>> sequences = new LinkedList<>();
    for (int i = 0; i < config.getSamples(); i++) {
      sequences.add(repository.readAll());
    }

    HashMap<String, List<BatteryProperty>> groupedProps = group(sequences);

    switch (config.getSamplingMethod()) {
      default:
        return computeMedian(groupedProps, propertyComparator);
    }
  }

  private static List<BatteryProperty> computeMedian(
      HashMap<String, List<BatteryProperty>> groupedProps, Comparator<BatteryProperty> comparator) {
    List<BatteryProperty> properties = new LinkedList<>();

    for (Entry<String, List<BatteryProperty>> entry : groupedProps.entrySet()) {
      List<BatteryProperty> grouping = entry.getValue();
      Collections.sort(grouping, comparator);
      properties.add(grouping.get(grouping.size() / 2));
    }

    return properties;
  }

  private static HashMap<String, List<BatteryProperty>> group(
      List<List<BatteryProperty>> sequences) {
    HashMap<String, List<BatteryProperty>> groupedProps = new HashMap<>();
    for (List<BatteryProperty> sequence : sequences) {
      for (BatteryProperty property : sequence) {
        if (groupedProps.containsKey(property.getName())) {
          groupedProps.get(property.getName()).add(property);
        } else {
          List<BatteryProperty> grouping = new LinkedList<>();
          grouping.add(property);
          groupedProps.put(property.getName(), grouping);
        }
      }

    }

    return groupedProps;
  }

  private final Comparator<BatteryProperty> propertyComparator = new Comparator<BatteryProperty>() {

    @Override
    public int compare(BatteryProperty o1, BatteryProperty o2) {
      if (o1.getValue() == o2.getValue()) {
        return 0;
      } else if (o1.getValue() > o2.getValue()) {
        return -1;
      } else {
        return 1;
      }
    }
  };

}
