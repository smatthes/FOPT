package gui.country.combo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountryInfo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model countries = new Model();
        Presenter presenter = new Presenter();
        View view = new View(presenter);
        presenter.setModelAndView(countries, view);
        presenter.init();

        Scene scene = new Scene(view.getUI());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
