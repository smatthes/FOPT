package gui.mvp.training;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EditorDialogDialog extends Dialog<TrainingUnit> {

    private TextField txtMarker;

    private TextField txtDistance;

    private TextField txtTime;

    private ButtonType btnAddType;

    public EditorDialogDialog() {
        super();
        initDialog();
    }

    private void initDialog() {
        setTitle("Neue Trainingseinheit");
        getDialogPane().setPadding(new Insets(20));
        btnAddType = new ButtonType("Hinzufügen", ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(btnAddType, ButtonType.CANCEL);
        getDialogPane().setContent(dialogPane());
        setResultConverter(bt -> createTrainingUnit(bt));
    }

    private TrainingUnit createTrainingUnit(ButtonType bt) {
        if (bt == btnAddType) {
            float distance = Float.parseFloat(txtDistance.getText());
            float time = Float.parseFloat(txtTime.getText());
            return new TrainingUnit(txtMarker.getText(), distance, time);
        }
        return null;
    }

    private Pane dialogPane() {
        GridPane dialogPane = new GridPane();
        dialogPane.setVgap(10);
        dialogPane.setHgap(20);

        dialogPane.add(new Label("Kennung (nicht leer):"), 0, 0);
        txtMarker = new TextField();
        dialogPane.add(txtMarker, 1, 0);

        dialogPane.add(new Label("Entfernung (in km):"), 0, 1);
        txtDistance = new TextField();
        dialogPane.add(txtDistance, 1, 1);

        dialogPane.add(new Label("Zeit (in Minuten):"), 0, 2);
        txtTime = new TextField();
        dialogPane.add(txtTime, 1, 2);

        return dialogPane;
    }

}
