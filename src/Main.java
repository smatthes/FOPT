import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String labelText = "FOPT Übung 4.2";
        VBox pane = new VBox(10);
        FlashingLabel label = new FlashingLabel(labelText);
        // label.setDelay(1_000L);
        label.setPadding(new Insets(20));
        label.setFont(Font.font(36));

        pane.getChildren().add(label);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setOnMouseClicked(event -> {
            System.out.println("Width: " + primaryStage.getWidth() + ", Height: " + primaryStage.getHeight());
            if (label.getText().equals(labelText)) {
                label.setText(new Date().toString());
            } else {
                label.setText(labelText);
            }
        });

        HBox boxDelay = new HBox(10);
        boxDelay.setAlignment(Pos.CENTER);
        Label lblDelay = new Label();
        boxDelay.getChildren().addAll(new Label("Delay:"), lblDelay);
        pane.getChildren().add(boxDelay);

        Slider slider = new Slider(250, 2000, 500);
        slider.setPadding(new Insets(20));
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(250);
        slider.setMinorTickCount(4);
        slider.setSnapToTicks(true);
        pane.getChildren().add(slider);
        label.delayProperty().bind(slider.valueProperty());
        lblDelay.textProperty().bind(new LongToStringBinding(label.delayProperty()));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FlashingLabel");
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            label.stopBlinking();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
