package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.PersonEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.PersonRepository;
import at.fhtw.swen2.tutorial.service.PersonMapper;
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

    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        personRepository.findAll().forEach(entity -> {
            personList.add(personMapper.fromEntity(entity));
        });
        return personList;
    }

    @Override
    public Person addnewPerson(Person person) {

        if (person == null){
            return null;
        }
        return personMapper.fromEntity(personRepository.save(personMapper.toEntity(person)));
    }

    public List<Person> getPersonListDummy() {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder().id(5L).name("John").isEmployed(true).build());
        personList.add(Person.builder().id(7L).name("Albert").isEmployed(true).build());
        personList.add(Person.builder().id(11L).name("Monica").isEmployed(true).build());
        return personList;
    }
}
