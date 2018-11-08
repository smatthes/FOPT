package gui.country.combo;

import javafx.beans.binding.Bindings;

public class Presenter {

    private View view;

    private Model model;

    public void setModelAndView(Model aModel, View aView) {
        model = aModel;
        view = aView;
    }

    public void init() {
        view.countriesProperty().bind(model.countryProperty());
        view.removeBtnDisableProperty().bind(Bindings.isEmpty(model.countryProperty()));
        if (model.countryProperty().size() > 0) {
            view.selectFirstCountry();
        }
    }

    public boolean addCountry(String country, String capital, String peopleString, String areaString) {
        boolean result = false;

        if (!country.trim().isEmpty() && !capital.trim().isEmpty() && !peopleString.isEmpty() && !areaString.isEmpty()) {
            try {
                long people = Long.parseUnsignedLong(peopleString);
                long area = Long.parseUnsignedLong(areaString);
                result = model.addCountry(new Country(country, capital, people, area));
            } catch (NumberFormatException ex) {
                // TODO: MessageBox öffnen
            }
        }

        return result;
    }

    public boolean deleteCountry(Country value) {
        return model.removeCountry(value);
    }

}
