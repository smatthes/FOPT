package gui.mvp.training;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View {

    private final Presenter presenter;

    private BorderPane root;

    private Button btnAdd;

    private Button btnDelete;

    private Label lblMarker;

    private Label lblDistance;

    private Label lblTime;

    private Label lblMeanSpeed;

    private ListView<TrainingUnit> listView;

    public View(Presenter presenter) {
        this.presenter = presenter;
        initView();
    }

    private void initView() {
        root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox boxCenter = new HBox(20);
        boxCenter.setPadding(new Insets(20, 0, 20, 20));
        listView = new ListView<>();
        listView.setId("overviewList");
        listView.setOnMouseClicked(e -> listViewSelectEvent());

        HBox boxBottom = new HBox(20);
        boxBottom.setPadding(new Insets(0, 0, 20, 20));
        btnAdd = new Button("Neue Trainingseinheit hinzufügen");
        // btnAdd.setOnAction(e -> presenter.addTraining());
        btnAdd.setOnAction(e -> showAddTrainingDialog());
        boxBottom.getChildren().add(btnAdd);
        btnDelete = new Button("Trainingseinheit löschen");
        btnDelete.setOnAction(e -> presenter.deleteTraining());
        boxBottom.getChildren().add(btnDelete);
        root.setBottom(boxBottom);

        HBox boxMarker = new HBox(10);
        lblMarker = new Label();
        lblMarker.setId("markerLabel");
        boxMarker.getChildren().addAll(new Label("Kennung:"), lblMarker);

        HBox boxDistance = new HBox(10);
        lblDistance = new Label();
        lblDistance.setId("distanceLabel");
        boxDistance.getChildren().addAll(new Label("Entfernung [km]:"), lblDistance);

        HBox boxTime = new HBox(10);
        lblTime = new Label();
        lblTime.setId("timeLabel");
        boxTime.getChildren().addAll(new Label("Zeit [Minuten]:"), lblTime);

        HBox boxMeanSpeed = new HBox(10);
        lblMeanSpeed = new Label();
        lblMeanSpeed.setId("meanSpeedLabel");
        lblMeanSpeed.setPrefWidth(40);
        boxMeanSpeed.getChildren().addAll(new Label("Durchschnittsgeschwindigkeit [km/h]:"), lblMeanSpeed);

        VBox boxRight = new VBox(20);
        boxRight.getChildren().addAll(boxMarker, boxDistance, boxTime, boxMeanSpeed);

        boxCenter.getChildren().addAll(listView, boxRight);
        root.setCenter(boxCenter);
    }

    private void showAddTrainingDialog() {
        TrainingUnit training = (new EditorDialog()).showDialog();
        
        if(Objects.nonNull(training)) {
            System.out.println("Neues Training " + training.getMarker());
        }
    }

    private void listViewSelectEvent() {
        presenter.trainingSelected();
    }

    public Parent getUI() {
        return root;
    }

    public ObservableList<TrainingUnit> listViewItems() {
        return listView.getItems();
    }

    public ObjectProperty<ObservableList<TrainingUnit>> listViewItemsProperty() {
        return listView.itemsProperty();
    }

    public TrainingUnit getSelectedItem() {
        return listView.getSelectionModel().getSelectedItem();
    }

    public void clearLabels() {
        lblMarker.setText("");
        lblDistance.setText("");
        lblTime.setText("");
        lblMeanSpeed.setText("");
    }

    public void updateLabels(TrainingUnit training) {
        String floatFormat = "%.1f";
        lblMarker.setText(training.getMarker());
        lblDistance.setText(String.format(floatFormat, training.getDistance()));
        lblTime.setText(String.format(floatFormat, training.getTime()));
        lblMeanSpeed.setText(String.format(floatFormat, training.getMeanSpeed()));
    }

}
