package gui.mvp.training;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditorDialog extends Stage {

    private TextField txtMarker;

    private TextField txtDistance;

    private TextField txtTime;

    private Button btnAdd;

    private Button btnCancel;
    
    private Label lblErrorMessage;
    
    private TrainingUnit training;

    public EditorDialog() {
        super();
        initView();
    }

    private void initView() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(30, 40, 30, 40));
        content.getChildren().addAll(dialogPane(), buttonPane(), errorMessagePane());
        Scene scene = new Scene(content);
        initModality(Modality.APPLICATION_MODAL);
        // setResizable(false);
        setScene(scene);
        setTitle("Neue Trainingseinheit");
    }

    private Parent dialogPane() {
        GridPane dialogPane = new GridPane();
        dialogPane.setVgap(10);
        dialogPane.setHgap(20);

        dialogPane.add(new Label("Kennung (nicht leer):"), 0, 0);
        txtMarker = new TextField();
        txtMarker.setId("markerTF");
        dialogPane.add(txtMarker, 1, 0);

        dialogPane.add(new Label("Entfernung (in km):"), 0, 1);
        txtDistance = new TextField();
        txtDistance.setId("distanceTF");
        dialogPane.add(txtDistance, 1, 1);

        dialogPane.add(new Label("Zeit (in Minuten):"), 0, 2);
        txtTime = new TextField();
        txtTime.setId("timeTF");
        dialogPane.add(txtTime, 1, 2);

        return dialogPane;
    }

    private Parent buttonPane() {
        HBox boxButton = new HBox(20);
        boxButton.setAlignment(Pos.CENTER_RIGHT);

        btnAdd = new Button("Hinzufügen");
        btnAdd.setOnAction(e -> addTraining());
        btnCancel = new Button("Abbrechen");
        btnCancel.setOnAction(e -> close());
        boxButton.getChildren().addAll(btnAdd, btnCancel);

        return boxButton;
    }
    
    private Parent errorMessagePane() {
        lblErrorMessage = new Label("");
        lblErrorMessage.setId("errMsgLabel");
        
        return lblErrorMessage;
    }

    private void addTraining() {
        long distance, time;        
        
        try {
            distance = Long.parseLong(txtDistance.getText());
        } catch (IllegalArgumentException ex) {
            lblErrorMessage.setText("Entfernung: ungültige Eingabe");
            return;
        }
        
        try {
            time = Long.parseLong(txtTime.getText());
        } catch (IllegalArgumentException ex) {
            lblErrorMessage.setText("Zeit: ungültige Eingabe");
            return;
        }
        
        try {
            training = new TrainingUnit(txtMarker.getText(), distance, time);
        } catch (Exception ex) {
            lblErrorMessage.setText(ex.getLocalizedMessage());
            return;
        }
        
        this.close();            
    }

    public TrainingUnit showDialog() {
        showAndWait();
        
        return training;
    }

}
