package com.crm.miniCRM.controller;

import com.crm.miniCRM.dto.AddressDto;
import com.crm.miniCRM.dto.PersonDto;
import com.crm.miniCRM.model.Address;
import com.crm.miniCRM.model.Event;
import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.interfaces.AddressRepository;
import com.crm.miniCRM.model.persistence.interfaces.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public String getAddresses(Model model) {
        Iterable<Address> addresses = addressRepository.findAll();
        List<AddressDto> addressDto = new ArrayList<>();
        addresses.forEach(p -> addressDto.add(convertToDto(p)));
        model.addAttribute("addresses", addressDto);
        return "addresses";
    }

    @GetMapping("/edit/{id}")
    public String editAddress(Model model, @PathVariable("id") Long id) {
        Optional<Address> address = null;
        address = addressRepository.findById(id);
        model.addAttribute("address", address);

        return "edit-address";
    }

    protected AddressDto convertToDto(Address entity) {
        return new AddressDto(entity.getId(), entity.getStreet(), entity.getNumber(), entity.getBox(), entity.getZip(), entity.getCity(), entity.getCountry(), entity.getType());
    }
}
