package com.book.address.controller;

import com.book.address.dto.PersonDTO;
import com.book.address.dto.ResponseDTO;
import com.book.address.model.Person;
import com.book.address.service.IAddrBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Provide ability to define restfull web services.
@RestController
// To map web request onto this handler class.
@RequestMapping("/addrbook/contact")
// Provide ability to print message over console.
@Slf4j
public class AddrBookController {

    // Declaring a list of person which are added, updated or deleted.
    private List<Person> persons = new ArrayList<>();

    @Autowired // Automatic dependency injection.
    IAddrBookService service;

    // Api to handle post request.
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> postPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = service.insertPerson(personDTO);
        log.info("Inserted new Person with Id = " + person.getId());

        // Adding a new person to list.
        persons.add(person);
        ResponseDTO responseDTO = new ResponseDTO("Person contact saved successfully!", person);

        // returning configured HTTP response.
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle get request by Person id.
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getPerson(@RequestParam int id) {

        Person person = service.selectPerson(id);
        ResponseDTO responseDTO = new ResponseDTO("Person '" + id + "' Profile: ", person);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle put request.
    @PutMapping("/put")
    public ResponseEntity<ResponseDTO> putPerson(@Valid @RequestParam int id, @RequestBody PersonDTO personDTO) {
        Person person = service.updatePerson(id, personDTO);

        // Adding an updated person to list.
        persons.add(person);

        ResponseDTO responseDTO = new ResponseDTO("Person details updated successfully!", person);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    // Api to handle delete request.
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deletePerson(@RequestParam int id) {
        Person person = service.deletePerson(id);

        // Adding an deleted person to a list.
        persons.add(person);

        ResponseDTO responseDTO = new ResponseDTO("Person '" + id + "' contact deleted successfully!", person);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle get request for all Persons.
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllPerson() {
        List<Person> persons = service.selectAllPerson();
        ResponseDTO responseDTO = new ResponseDTO("Profile of all Persons: ", persons);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyname")
    public ResponseEntity<ResponseDTO> getPersonByName(@RequestParam String fname, String lname) {
        List<Person> persons = service.findPersonByName(fname, lname);
        ResponseDTO responseDTO = new ResponseDTO("Profile of 'Persons' having same name: ", persons);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
