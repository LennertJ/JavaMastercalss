package com.crm.miniCRM.Util;

import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.interfaces.PersonRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVreader {

    private final PersonRepository personRepository;

    public CSVreader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public void read() {
        System.out.println("enters reader");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/persons.csv"))) {
            String line;
            System.out.println("open file");
            while ((line = reader.readLine()) != null) {
                personRepository.save(lineToPerson(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Person lineToPerson(String line) {
        String[] dataPoints = line.split(",");
        if(dataPoints.length!=3){
            throw new RuntimeException();
        }
        return new Person(dataPoints[0],dataPoints[1], stringToLocalDate(dataPoints[2]));
    }

    private LocalDate stringToLocalDate(String dataPoint) {
        int year = Integer.parseInt(dataPoint.substring(6, 10));
        int month = Integer.parseInt(dataPoint.substring(3, 5));
        int day = Integer.parseInt(dataPoint.substring(0, 2));
        return LocalDate.of(year, month, day);
    }
}
