package gui.country.combo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CountryInfo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model countries = new Model();
        initCountryModel(countries);
        Presenter presenter = new Presenter();
        View view = new View(presenter);
        presenter.setModelAndView(countries, view);
        presenter.init();

        Scene scene = new Scene(view.getUI());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
//        primaryStage.getIcons().add(new Image("file:resources/icons/globe-icon-16.png"));
//        primaryStage.getIcons().add(new Image("file:resources/icons/globe-icon-24.png"));
//        primaryStage.getIcons().add(new Image("file:resources/icons/globe-icon-32.png"));
//        primaryStage.getIcons().add(new Image("file:resources/icons/globe-icon-48.png"));
//        primaryStage.getIcons().add(new Image("file:resources/icons/globe-icon-64.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private static void initCountryModel(Model model) {
        model.addCountry(new Country("Deutschland", "Berlin", 82_114_224, 357_121));
        model.addCountry(new Country("Belgien", "Brüssel", 11_429_336, 32_545));
        model.addCountry(new Country("Schweiz", "Bern", 8_476_005, 41_285));
        model.addCountry(new Country("Polen", "Warschau", 38_170_712, 312_685));
        model.addCountry(new Country("Dänemark", "Kopenhagen", 5_733_551, 43_098));
    }

}
