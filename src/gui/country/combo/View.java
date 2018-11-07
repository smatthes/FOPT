package gui.country.combo;

import java.util.Objects;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class View {

    private Button btnAdd = new Button("Hinzufügen");

    private Button btnDelete = new Button("Löschen");

    private CheckBox cbExactValues = new CheckBox("exakte Angaben");

    private ComboBox<Country> cmbCountries = new ComboBox<>();

    private Label lblCountry = new Label();

    private Label lblCapital = new Label();

    private Label lblPeople = new Label();

    private Label lblArea = new Label();

    private Label lblDensity = new Label();

    private TextField txtCountryName = new TextField();

    private TextField txtCapital = new TextField();

    private TextField txtPeople = new TextField();

    private TextField txtArea = new TextField();

    private Pane root;

    private Presenter presenter;

    public View(Presenter presenter) {
        this.presenter = presenter;
        initPanel();
    }

    public Pane getUI() {
        return root;
    }

    private void initPanel() {
        root = new VBox(10);
        Insets padding = new Insets(20);
        root.setPadding(padding);

        cmbCountries.setId("countrySelector");
        cmbCountries.setPromptText("Keine Länder vorhanden");
        cmbCountries.valueProperty().addListener(countryListener());
        cmbCountries.setEditable(false);        
        root.getChildren().add(cmbCountries);

        cbExactValues.setId("exactValues");
        cbExactValues.setSelected(true);
        cbExactValues.setOnAction (e -> updateLabels(cmbCountries.getValue()));
        root.getChildren().add(cbExactValues);

        GridPane dataPane = new GridPane();
        dataPane.setHgap(10);
        dataPane.setVgap(10);
        dataPane.add(new Label("Land:"), 0, 0);
        dataPane.add(new Label("Hauptstadt:"), 0, 1);
        dataPane.add(new Label("Einwohner:"), 0, 2);
        dataPane.add(new Label("Fläche (in qkm):"), 0, 3);
        dataPane.add(new Label("Bevölkerungsdichte (in Personen pro qkm):"), 0, 4);

        lblCountry.setId("countryName");
        dataPane.add(lblCountry, 1, 0);
        lblCapital.setId("capital");
        dataPane.add(lblCapital, 1, 1);
        lblPeople.setId("population");
        dataPane.add(lblPeople, 1, 2);
        lblArea.setId("area");
        dataPane.add(lblArea, 1, 3);
        lblDensity.setId("density");
        dataPane.add(lblDensity, 1, 4);
        root.getChildren().add(dataPane);

        HBox inputPane = new HBox(10);
        txtCountryName.setId("countryField");
        txtCountryName.setPromptText("Land");        
        // txtCountryName.setOnKeyReleased(e -> handleKeyboardEnter(e));
        inputPane.getChildren().add(txtCountryName);
        
        txtCapital.setId("capitalField");
        txtCapital.setPromptText("Hauptstadt");        
        // txtCapital.setOnKeyReleased(e -> handleKeyboardEnter(e));        
        inputPane.getChildren().add(txtCapital);
        
        txtPeople.setId("populationField");
        txtPeople.setPromptText("Einwohner");        
        // txtPeople.setOnKeyReleased(e -> handleKeyboardEnter(e));
        inputPane.getChildren().add(txtPeople);
        
        txtArea.setId("areaField");
        txtArea.setPromptText("Fläche");
        // txtArea.setOnKeyReleased(e -> handleKeyboardEnter(e));
        inputPane.getChildren().add(txtArea);

        btnAdd.setId("add");
        btnAdd.setOnAction(e -> addCountry());
        btnAdd.setOnKeyReleased(e -> handleKeyboardEnter(e));
        inputPane.getChildren().add(btnAdd);
        root.getChildren().add(inputPane);

        btnDelete.setId("delete");
        btnDelete.setOnAction(e -> deleteCountry());
        // btnDelete.disableProperty().bind(Bindings.isEmpty(cmbCountries.getItems()));
        
        root.getChildren().add(btnDelete);        

        clearTextFields();
    }

    private void handleKeyboardEnter(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) {
            addCountry();
        }
    }

    private ChangeListener<? super Country> countryListener() {
        return (observable, prevSelectedCountry, selectedCountry) -> {
            if(Objects.nonNull(selectedCountry)) {
                updateLabels(selectedCountry);
            }
        };
    }

    public void clearLabels() {
        lblCountry.setText("");
        lblCapital.setText("");
        lblPeople.setText("");
        lblArea.setText("");
        lblDensity.setText("");
    }

    public void clearTextFields() {
        txtCountryName.clear();
        txtCapital.clear();
        txtPeople.clear();
        txtArea.clear();
    }

    public ObjectProperty<ObservableList<Country>> countriesProperty() {
        return cmbCountries.itemsProperty();
    }

    private void addCountry() {
        if (presenter.addCountry(txtCountryName.getText(), txtCapital.getText(), txtPeople.getText(), txtArea.getText())) {
            clearTextFields();
            cmbCountries.getSelectionModel().selectLast();
            btnDelete.disableProperty().setValue(false);
        }
    }
    
    private void deleteCountry() {
        if (presenter.deleteCountry(cmbCountries.getValue())) {
            clearLabels();
            clearTextFields();
            selectFirstCountry();   
            btnDelete.disableProperty().setValue(cmbCountries.getItems().size() == 0);
        }
    }
    
    private String numberFormatter(long number) {
        if(!cbExactValues.isSelected()) {
            if(number > 1_000_000) {
                return String.format("%d Mill.", Math.round(number / 1_000_000f));
            } else if(number > 1_000) {
                return String.format("%,d.000", Math.round(number / 1_000f));
            }
        }
        return String.format("%,d", number);
    }
    
    public void selectFirstCountry() {
        cmbCountries.getSelectionModel().selectFirst();
    }
    
    private void updateLabels(Country country) {
        lblCountry.setText(country.getName());
        lblCapital.setText(country.getCapital());
        lblPeople.setText(numberFormatter(country.getPeople()));
        lblArea.setText(numberFormatter(country.getArea()));
        lblDensity.setText(numberFormatter(country.getDensity()));
    }

}
