package gui.mvp.training;

import java.util.Objects;
import java.util.Optional;

import javafx.scene.control.Dialog;

public class Presenter {

    private Model model;

    private View view;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void init() {
        updateListView();
    }

    private void updateListView() {
        view.listViewItems().clear();
        view.listViewItems().addAll(model.getAllTrainingsUnits());
    }

    public void trainingSelected() {
        TrainingUnit training = view.getSelectedItem();
        if (training != null) {
            view.updateLabels(view.getSelectedItem());
        }
    }

    public void deleteTraining() {
        TrainingUnit training = view.getSelectedItem();
        if (Objects.nonNull(training)) {
            model.removeTrainingUnit(training.getMarker());
            updateListView();
            view.clearLabels();
        }
    }

    public void addTraining() {
        Dialog<TrainingUnit> dialog = new EditorDialogDialog();
        Optional<TrainingUnit> result = dialog.showAndWait();
        if (result.isPresent()) {
            model.addTrainingUnit(result.get());
            updateListView();
        }
    }

}
