package com.crm.miniCRM.model.persistence.interfaces;


import com.crm.miniCRM.model.PersonAddress;
import com.crm.miniCRM.model.persistence.helpers.PersonAddressID;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonAddressRepository extends CrudRepository<PersonAddress, PersonAddressID> {

    Optional<PersonAddress> findById(PersonAddressID personAddressID);

}