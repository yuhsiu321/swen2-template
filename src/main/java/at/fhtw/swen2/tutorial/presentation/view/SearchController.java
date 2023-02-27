package at.fhtw.swen2.tutorial.presentation.view;


import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.presentation.model.PersonView;
import at.fhtw.swen2.tutorial.service.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SearchController {

    //@Autowired private LeftPaneController leftPaneController;

    @FXML
    public Button updateButton;
    @Autowired
    private PersonService personService;

    public static final int PAGE_ITEMS_COUNT = 10;

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchLabel;
    @FXML
    private TableView tableView;
    @FXML
    private VBox dataContainer;

    private ObservableList<PersonView> masterData = FXCollections.observableArrayList();
    private ObservableList<PersonView> results = FXCollections.observableList(masterData);

    @PostConstruct
    void init() {
        personService.getPersonList().forEach(p -> masterData.add(new PersonView(p)));
    }

    @FXML
    private void initialize() {

        // search panel
        searchButton.setText("Search");
        searchButton.setOnAction(event -> loadData());
        searchButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");

        searchField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                loadData();
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
        });

        initTable();

    }

    private void initTable() {
        tableView = new TableView<>(FXCollections.observableList(masterData));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn employed = new TableColumn("EMPLOYED");
        employed.setCellValueFactory(new PropertyValueFactory("isEmployed"));
        tableView.getColumns().addAll(id, name, employed);


        dataContainer.getChildren().add(tableView);
    }

    public void addPerson(Person person){
        masterData.add(new PersonView(person));
        tableView.refresh();
    }

    private void loadData() {

        init();

        String searchText = searchField.getText().toLowerCase();

        Task<ObservableList<PersonView>> task = new Task<ObservableList<PersonView>>() {
            @Override
            protected ObservableList<PersonView> call() throws Exception {
                updateMessage("Loading data");
                return FXCollections.observableArrayList(masterData
                        .stream()
                        .filter(value -> value.getName().toLowerCase().contains(searchText))
                        .collect(Collectors.toList()));
            }
        };

        task.setOnSucceeded(event -> {
            results = task.getValue();
            tableView.setItems(FXCollections.observableList(results));
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

}
