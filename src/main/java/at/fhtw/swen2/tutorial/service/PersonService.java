package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.service.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersonList();

    Person addNewPerson(Person person);

}
