package com.book.address.service;

import com.book.address.dto.PersonDTO;
import com.book.address.model.Person;

import java.util.List;

public interface IAddrBookService {
    // Declaring abstract method just for CRUD operations.
    Person insertPerson(PersonDTO personDTO);

    Person selectPerson(int personId);

    Person updatePerson(int personId, PersonDTO personDTO);

    Person deletePerson(int personId);

    // Abstract method to get all Person details.
    List<Person> selectAllPerson();

    // Abstract method to find person by name.
    List<Person> findPersonByName(String fname, String lname);
}
