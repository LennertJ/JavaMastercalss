package com.crm.miniCRM.service.interfaces;

import com.crm.miniCRM.controller.model.Person;

public interface IPersonService {
    void softDeleteById(Long id);
    void savePerson(Person person);
}
