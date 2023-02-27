package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.persistence.PersonEntity;
import at.fhtw.swen2.tutorial.service.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person fromEntity(PersonEntity personEntity){
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .isEmployed(personEntity.isEmployed())
                .build();
    }

    public PersonEntity toEntity(Person person){
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .isEmployed(person.isEmployed())
                .build();
    }

}
