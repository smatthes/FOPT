package gui.mvp.training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Model {

    private ListProperty<TrainingUnit> trainingsList;

    private List<String> markerList;

    public Model() {
        trainingsList = new SimpleListProperty<>(FXCollections.observableArrayList());
        markerList = new ArrayList<>();
        addSampleData();
    }

    public void addTrainingUnit(TrainingUnit unit) {
        String newMarker = unit.getMarker().trim();
        if (markerList.contains(newMarker)) {
            throw new IllegalArgumentException("Es existiert bereits ein Eintrag mit dem Marker " + newMarker);
        }

        addTraining(unit);
    }

    public void removeTrainingUnit(String kennung) {
        TrainingUnit training = getTrainingUnit(kennung);

        if (Objects.nonNull(training)) {
            markerList.remove(kennung);
            removeTraining(training);
        }
    }

    public TrainingUnit getTrainingUnit(String kennung) {
        TrainingUnit training = null;

        if (Objects.nonNull(kennung)) {
            int index = markerList.indexOf(kennung);
            if (index >= 0) {
                training = trainingsList.get(index);
            }
        }

        return training;
    }

    public ListProperty<TrainingUnit> trainingsListProperty() {
        return trainingsList;
    }

    public Collection<TrainingUnit> getAllTrainingsUnits() {
        return trainingsList.get();
    }

    public String[] getAllMarkers() {
        String[] markers = new String[trainingsList.size()];

        return markerList.toArray(markers);
    }

    private void addTraining(TrainingUnit training) {
        if (markerList.add(training.getMarker())) {
            trainingsList.add(training);
        }
    }

    private void removeTraining(TrainingUnit training) {
        trainingsList.remove(training);
    }

    private void addSampleData() {
        addTrainingUnit(new TrainingUnit("Lauf am Montag", 3, 32));
        addTrainingUnit(new TrainingUnit("Lauf am Dienstag", 3, 35));
        addTrainingUnit(new TrainingUnit("Lauf am Mittwoch", 2, 14));
        addTrainingUnit(new TrainingUnit("Radfahrt am Freitag", 35, 90));
    }

}
