package com.crm.miniCRM.dto;

import com.crm.miniCRM.model.Community;
import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.helpers.MemberID;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberDto {
    private Community community;
    private LocalDate since;
    private LocalDate until;//default 9999-21-31
    private ArrayList<Person> persons;
    private String personNames;

    public MemberDto(Community community, Person person, LocalDate since, LocalDate until) {
        this.community = community;
        this.since = since;
        this.until = until;
        this.persons = new ArrayList<>();
        this.persons.add(person);
        this.personNames = person.getFirstName()+ "; ";
    }

    public Community getCommunity() {
        return community;
    }

    public void addPersonToCommunity(Person person){
        this.persons.add(person);
        this.personNames+= person.getFirstName()+ "; ";
    }

    public LocalDate getSince() {
        return since;
    }

    public LocalDate getUntil() {
        return until;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public String getPersonNames() {
        return personNames;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "community=" + community +
                ", since=" + since +
                ", until=" + until +
                ", persons=" + persons +
                ", personNames='" + personNames + '\'' +
                '}';
    }
}
