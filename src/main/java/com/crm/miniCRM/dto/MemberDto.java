package com.crm.miniCRM.dto;

import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.helpers.MemberID;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberDto {
    private long communityId;
    private LocalDate since;
    private LocalDate until;//default 9999-21-31
    private ArrayList<Person> persons;
    private String personNames;

    public MemberDto(long id, Person person, LocalDate since, LocalDate until) {
        this.communityId = id;
        this.since = since;
        this.until = until;
        this.persons = new ArrayList<>();
        this.persons.add(person);
        this.personNames = person.getFirstName()+ "; ";
    }

    public long getCommunityId() {
        return communityId;
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
}
