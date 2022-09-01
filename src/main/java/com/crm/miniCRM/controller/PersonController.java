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
        System.out.println(person.toString());
        if(personService.findById(person.getId()).orElse(null)==null){
            personService.save(convertToEntity(person));
        }else{
            personService.save(convertToEntity(person,"2022-02-22"));
        }
        return "redirect:/persons";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(Model model,@PathVariable("id") Long id) {
        Person person = personService.findById(id).orElse(null);
        if(person==null){
            model.addAttribute("errorMessage", "Person not found");
            return "redirect:/persons";
        }
        person.setBirthDayAsString(person.getBirthDay().toString());
        model.addAttribute("person", person);
        //System.out.println(model.toString());
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

    protected Person convertToEntity(PersonDto dto, String birtdayAsString) {
        System.out.println(dto);
        //29-06-1963
        int year = Integer.parseInt(birtdayAsString.substring(0, 4));
        int month = Integer.parseInt(birtdayAsString.substring(5, 7));
        int day = Integer.parseInt(birtdayAsString.substring(8, 10));
        Person person = new Person(dto.getFirstName(), dto.getLastName(), LocalDate.of(year, month, day));
        if (!StringUtils.isEmpty(dto.getId())) {
            person.setId(dto.getId());
        }
        return person;
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
