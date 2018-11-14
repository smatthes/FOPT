package gui.mvp.training;

import java.util.Collection;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Model {

    private ObservableMap<String, TrainingUnit> trainingsMap;

    public Model() {
        trainingsMap = FXCollections.observableHashMap();
        addSampleData();
    }

    public void addTrainingUnit(TrainingUnit unit) {
        String newMarker = unit.getMarker().trim();
        if (trainingsMap.containsKey(newMarker)) {
            throw new IllegalArgumentException("Es existiert bereits ein Eintrag mit dem Marker " + newMarker);
        }

        trainingsMap.put(newMarker, unit);
    }

    public void removeTrainingUnit(String kennung) {
        if (Objects.nonNull(kennung) && trainingsMap.containsKey(kennung)) {
            trainingsMap.remove(kennung);
        }
    }

    public TrainingUnit getTrainingUnit(String kennung) {
        return trainingsMap.get(kennung);
    }

    public Collection<TrainingUnit> getAllTrainingsUnits() {
        return trainingsMap.values();
    }

    public String[] getAllMarkers() {
        String[] markers = new String[trainingsMap.keySet().size()];

        return trainingsMap.keySet().toArray(markers);
    }

    private void addSampleData() {
        addTrainingUnit(new TrainingUnit("Lauf am Montag", 3, 32));
        addTrainingUnit(new TrainingUnit("Lauf am Dienstag", 3, 35));
        addTrainingUnit(new TrainingUnit("Lauf am Mittwoch", 2, 14));
    }

}
