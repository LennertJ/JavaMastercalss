package com.crm.miniCRM.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crm.miniCRM.dto.PersonDto;
import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.interfaces.PersonRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/persons")
public class PersonController {

    private PersonRepository personService;

    public PersonController(PersonRepository personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getpersons(Model model) {
        Iterable<Person> persons = personService.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        persons.forEach(p -> personDtos.add(convertToDto(p)));
        model.addAttribute("persons", personDtos);
        return "persons";
    }

    @GetMapping("/new")
    public String newperson(Model model) {
        model.addAttribute("person", new PersonDto());
        return "new-person";
    }

    @GetMapping("/edit")
    public String register() {
        return "redirect:/";
    }

    @PostMapping
    public String addperson(PersonDto person) {
        personService.save(convertToEntity(person));

        return "redirect:/persons";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(Model model,@PathVariable("id") Long id) {
        Optional<Person> person = null;
        try {
            person = personService.findById(id);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Person not found");
        }
        model.addAttribute("person", person);

        return "edit-person";
    }

    @PostMapping("/savePerson")
    public String savePerson(@ModelAttribute("person") Person person) {
        // save employee to database
        personService.save(person);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {

        personService.save(personService.softDeleteById(id));
        return "redirect:/persons";
    }

    protected PersonDto convertToDto(Person entity) {
        return new PersonDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getBirthDay().toString(), entity.isActive());
    }

    protected Person convertToEntity(PersonDto dto) {
        //29-06-1963
        int year = Integer.parseInt(dto.getBirthDay().toString().substring(6, 10));
        int month = Integer.parseInt(dto.getBirthDay().toString().substring(3, 5));
        int day = Integer.parseInt(dto.getBirthDay().toString().substring(0, 2));
        Person person = new Person(dto.getFirstName(), dto.getLastName(), LocalDate.of(year, month, day));
        if (!StringUtils.isEmpty(dto.getId())) {
            person.setId(dto.getId());
        }
        return person;
    }


}
