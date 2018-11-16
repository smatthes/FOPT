package gui.mvp.training;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        model.addSampleData();
        Presenter presenter = new Presenter();
        View view = new View(presenter);
        presenter.setModel(model);
        presenter.setView(view);
        presenter.init();

        Scene scene = new Scene(view.getUI());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        // primaryStage.setResizable(false);
        primaryStage.setTitle("Trainingseinheiten");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
