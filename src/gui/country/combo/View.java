package gui.country.combo;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        cmbCountries.getSelectionModel().selectFirst();
        cmbCountries.setEditable(false);
        root.getChildren().add(cmbCountries);

        cbExactValues.setId("exactValues");
        cbExactValues.setSelected(true);
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
        inputPane.getChildren().add(txtCountryName);
        inputPane.getChildren().add(txtCapital);
        inputPane.getChildren().add(txtPeople);
        inputPane.getChildren().add(txtArea);

        btnAdd.setId("add");
        btnAdd.setOnAction(e -> addCountry());

        inputPane.getChildren().add(btnAdd);
        root.getChildren().add(inputPane);

        btnDelete.setId("delete");
        root.getChildren().add(btnDelete);

        emptyTextFields();
    }

    public void emptyLabel() {
        lblCountry.setText("");
        lblCapital.setText("");
        lblPeople.setText("");
        lblArea.setText("");
    }

    public void emptyTextFields() {
        txtCountryName.setText("Land");
        txtCapital.setText("Hauptstadt");
        txtPeople.setText("Einwohner");
        txtArea.setText("Fläche");
    }

    public ObjectProperty<ObservableList<Country>> countriesProperty() {
        return cmbCountries.itemsProperty();
    }

    private void addCountry() {
        if (presenter.addCountry(txtCountryName.getText(), txtCapital.getText(), txtPeople.getText(), txtArea.getText())) {
            emptyTextFields();
            cmbCountries.getSelectionModel().selectFirst();
        }
    }

}
