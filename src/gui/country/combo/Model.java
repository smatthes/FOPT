package gui.country.combo;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private ObservableList<Country> countryList = FXCollections.observableArrayList();

    private SimpleListProperty<Country> countries = new SimpleListProperty<>(countryList);

    public boolean addCountry(Country country) {
        return countries.add(country);
    }

    public ListProperty<Country> countryProperty() {
        return countries;
    }

    public boolean removeCountry(Country country) {
        return countries.remove(country);
    }

}
