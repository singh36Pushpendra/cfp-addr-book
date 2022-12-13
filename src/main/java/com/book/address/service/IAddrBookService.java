package com.book.address.service;

import com.book.address.dto.PersonDTO;
import com.book.address.dto.ResponseDTO;
import com.book.address.model.Email;
import com.book.address.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAddrBookService {
    // Declaring abstract methods just for CRUD operations.
    Person insertPerson(PersonDTO personDTO);

    Person selectPerson(int personId);

    Person updatePerson(int personId, PersonDTO personDTO);

    Person deletePerson(int personId);

    // Abstract method to get all Person details.
    List<Person> selectAllPerson();

    // Abstract method to find person by name.
    List<Person> findPersonByName(String fname, String lname);

    // Abstract method to select person by token.
    Person selectPerson(String token);

    // Abstract method to update person by token.
    Person updatePerson(String token, PersonDTO personDTO);

    // Abstract method to delete person by token.
    Person deletePerson(String token);

    // Abstract method to get token from person id.
    String getToken(int personId);

}
