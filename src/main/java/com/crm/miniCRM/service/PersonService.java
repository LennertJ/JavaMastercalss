package com.crm.miniCRM.service;

import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.interfaces.PersonRepository;
import com.crm.miniCRM.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void softDeleteById(Long id) {
        personRepository.softDeleteById(id);
    }
    public void findById(Long id) {
        personRepository.findById(id);
    }
    public void savePerson(Person person) {
         personRepository.save(person);
    }


}
