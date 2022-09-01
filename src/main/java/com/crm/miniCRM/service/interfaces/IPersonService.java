package com.crm.miniCRM.service.interfaces;

import com.crm.miniCRM.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    //List<Person> findByLastName(String lastName);

    //Person findById(long id);

    //Optional<Person> findById(Long aLong);

    void softDeleteById(Long id);
}
