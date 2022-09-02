package com.crm.miniCRM.controller.model.persistence.interfaces;


import com.crm.miniCRM.controller.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);

    Person findById(long id);

    default Person softDeleteById(Long id){
        Person person = findById(id).orElse(null);
        if(person==null){
            return null;
        }
        person.setActive(false);
        return person;
    }

    default void savePerson(Person person){
        Optional<Person> previous = findById(person.getId());


    }
}