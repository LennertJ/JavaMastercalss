package com.crm.miniCRM.controller.model.persistence.interfaces;


import com.crm.miniCRM.controller.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByStreet(String street);

    Address findById(long id);
}