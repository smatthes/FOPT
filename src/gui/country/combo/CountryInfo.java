package gui.country.combo;

import javafx.application.Application;
import javafx.stage.Stage;

public class CountryInfo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
