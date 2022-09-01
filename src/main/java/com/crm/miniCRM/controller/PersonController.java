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

    private final PersonRepository personService;

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

    @PostMapping
    public String addperson(PersonDto person) {
        if(person.getId()==null){
            personService.save(convertToEntity(person));
        }else{
            personService.save(convertToEntityEdit(person));
        }
        return "redirect:/persons";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(Model model,@PathVariable("id") Long id) {
        Person p = personService.findById(id).orElse(null);
        if(p==null){
            model.addAttribute("errorMessage", "Person not found");
            return "redirect:/persons";
        }
        PersonDto personDto =
                new PersonDto(p.getId(), p.getFirstName(), p.getLastName(), p.getBirthDay().toString(), p.isActive());
        model.addAttribute("person", personDto);
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

    protected Person convertToEntityEdit(PersonDto dto) {
        int year = Integer.parseInt(dto.getBirthDay().substring(0, 4));
        int month = Integer.parseInt(dto.getBirthDay().substring(5, 7));
        int day = Integer.parseInt(dto.getBirthDay().substring(8, 10));
        Person person = new Person(dto.getFirstName(), dto.getLastName(), LocalDate.of(year, month, day));
        if (!StringUtils.isEmpty(dto.getId())) {
            person.setId(dto.getId());
        }
        return person;
    }

    protected Person convertToEntity(PersonDto dto) {
        int year = Integer.parseInt(dto.getBirthDay().substring(6, 10));
        int month = Integer.parseInt(dto.getBirthDay().substring(3, 5));
        int day = Integer.parseInt(dto.getBirthDay().substring(0, 2));
        Person person = new Person(dto.getFirstName(), dto.getLastName(), LocalDate.of(year, month, day));
        if (!StringUtils.isEmpty(dto.getId())) {
            person.setId(dto.getId());
        }
        return person;
    }

}
