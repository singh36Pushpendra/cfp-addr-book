package com.book.address.model;

import com.book.address.dto.PersonDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// Used to map object to corresponding table in database.
@Entity
//
@Getter
@NoArgsConstructor
@Slf4j
public class Person {
    // Applying setter on id only.
    @Setter
    // To make primary key in table.
    @Id
    // To auto increment id value in table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fname, lname;

    private long phoneNum;

    private String address, city, state;

    private int zipCode;

    public Person(PersonDTO personDTO) {
        log.info("This is inside 'Person' class constructor!");
        fname = personDTO.fname;
        lname = personDTO.lname;
        phoneNum = personDTO.phoneNum;
        address = personDTO.address;
        city = personDTO.city;
        state = personDTO.state;
        zipCode = personDTO.zipCode;
    }
}
