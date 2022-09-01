package com.crm.miniCRM.model.persistence.implementations;

import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.interfaces.PersonRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
@Repository
@Primary
public class PersonRepositoryImpl implements PersonRepository {
    List<Person> persons = new ArrayList<>();

    @Override
    public void softDeleteById(Long id) {
        Person person = findById(id).orElse(null);
        if(person==null){
            return;
        }
        person.setNotDeleted(false);
    }
}
*/