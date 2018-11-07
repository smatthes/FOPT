package gui.country.combo;

public class Presenter {

    private View view;

    private Model model;

    public void setModelAndView(Model aModel, View aView) {
        model = aModel;
        view = aView;
    }

    public void init() {
        view.countriesProperty().bind(model.countryProperty());
    }

    public boolean addCountry(String country, String capital, String peopleString, String areaString) {
        boolean result = false;

        try {
            long people = Long.parseUnsignedLong(peopleString);
            long area = Long.parseUnsignedLong(areaString);
            result = model.addCountry(new Country(country, capital, people, area));
        } catch (NumberFormatException ex) {
            // TODO: MessageBox öffnen
        }

        return result;
    }

}
