package at.fhtw.swen2.tutorial.presentation.model;

import at.fhtw.swen2.tutorial.service.model.Person;
import javafx.beans.property.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class PersonView {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleBooleanProperty isEmployed;

    private final Person person;

    public PersonView(Person person) {
        this.person = person;
        this.id = new SimpleLongProperty(person.getId());
        this.name = new SimpleStringProperty(person.getName());
        this.isEmployed = new SimpleBooleanProperty(person.isEmployed());
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean getIsEmployed() {
        return isEmployed.get();
    }

    public BooleanProperty isEmployedProperty() {
        return isEmployed;
    }

    public void setIsEmployed(boolean isEmployed) {
        this.isEmployed.set(isEmployed);
    }


}
