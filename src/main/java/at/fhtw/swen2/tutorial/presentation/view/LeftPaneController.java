package at.fhtw.swen2.tutorial.presentation.view;

import java.net.URL;
import java.util.ResourceBundle;

import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.fxml.Initializable;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LeftPaneController implements Initializable {

    @Autowired
    private PersonService personService;

    @Autowired
    private SearchController searchController;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmployed;
    @FXML
    public Button sendButton;

    @FXML
    public void initialize(URL location, ResourceBundle rb) {

        sendButton.setOnAction(event -> create());
    }

    private void create(){

        boolean isEmployed;
        if(tfEmployed.getText().toLowerCase().equals("true")){
            isEmployed = true;
        }else if(tfEmployed.getText().toLowerCase().equals("false")){
            isEmployed = false;
        }else{
            isEmployed = false;
        }

        Person person = Person.builder()
                .name(tfName.getText())
                .isEmployed(isEmployed)
                .build();

        person = personService.addNewPerson(person);

        searchController.addPerson(person);
    }
}
