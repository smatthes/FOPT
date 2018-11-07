package gui.mvp.training;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Trainingseinheiten");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
