package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.PersonEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.PersonRepository;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        personRepository.findAll().forEach(entity -> {
            personList.add(Person.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .isEmployed(entity.isEmployed())
                    .build());
        });
        //System.out.println(personList.toString());
        return personList;
    }

    @Override
    public Person addnewPerson(Person person) {

        PersonEntity personEntity = personRepository.save(PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .isEmployed(person.isEmployed())
                .build());

        Person p = Person.builder().id(personEntity.getId()).name(personEntity.getName()).isEmployed(personEntity.isEmployed()).build();
        //System.out.println(person.toString());
        //personRepository.findAll().forEach(System.out::println);
        //this.getPersonList();
        return p;
    }

    public List<Person> getPersonListDummy() {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder().id(5L).name("John").isEmployed(true).build());
        personList.add(Person.builder().id(7L).name("Albert").isEmployed(true).build());
        personList.add(Person.builder().id(11L).name("Monica").isEmployed(true).build());
        return personList;
    }
}
