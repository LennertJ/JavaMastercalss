package com.crm.miniCRM.Util;

import com.crm.miniCRM.MiniCrmApplication;
import com.crm.miniCRM.controller.model.Person;
import com.crm.miniCRM.controller.model.persistence.interfaces.PersonRepository;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class CSVreader {
    private final PersonRepository personRepository;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MiniCrmApplication.class);
    public CSVreader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public void read() {
        log.info("enters reader");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/persons.csv"))) {
            String line;
            log.info("open file");
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
