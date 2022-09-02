package com.crm.miniCRM.controller.model.persistence.implementations;

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