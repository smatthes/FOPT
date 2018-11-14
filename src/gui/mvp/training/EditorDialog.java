package gui.mvp.training;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditorDialog extends Stage {

    private Presenter presenter;

    private TextField txtMarker;

    private TextField txtDistance;

    private TextField txtTime;

    private Button btnAdd;

    private Button btnCancel;

    public EditorDialog() {
        super();
        initView();
    }

    private void initView() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.getChildren().addAll(dialogPane(), buttonPane());
        Scene scene = new Scene(content);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setScene(scene);
        setTitle("Neue Trainingseinheit");
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

    private Pane buttonPane() {
        HBox boxButton = new HBox(10);
        boxButton.setAlignment(Pos.CENTER_RIGHT);

        btnAdd = new Button("Hinzufügen");
        btnCancel = new Button("Abbrechen");
        btnCancel.setOnAction(e -> close());
        boxButton.getChildren().addAll(btnAdd, btnCancel);

        return boxButton;
    }

}
