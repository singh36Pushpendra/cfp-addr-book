package com.book.address.controller;

import com.book.address.dto.PersonDTO;
import com.book.address.dto.ResponseDTO;
import com.book.address.model.Email;
import com.book.address.model.Person;
import com.book.address.service.IAddrBookService;
import com.book.address.service.IEmailService;
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

    // Declaring a list of person which will be added, updated or deleted.
    private List<Person> persons = new ArrayList<>();

    @Autowired // Automatic dependency injection.
    IAddrBookService service;

    @Autowired
    IEmailService emailService;

    // Api to handle post request.
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> postPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = service.insertPerson(personDTO);
        log.info("Inserted new Person with Id = " + person.getId());

        // Adding a new person to list.
        persons.add(person);

        String token = service.getToken(person.getId());
        ResponseDTO responseDTO = new ResponseDTO("Person contact saved successfully!", person, token);
        Email email = new Email(person.getEmail(), "Message For Code Checking!", token);

        emailService.sendMail(email);

        // returning configured HTTP response.
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle get request by Person id.
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getPerson(@RequestParam int id) {

        Person person = service.selectPerson(id);
        ResponseDTO responseDTO = new ResponseDTO("Person '" + id + "' Profile: ", person, service.getToken(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle get request by Person token.
    // Overloaded Method.
    @GetMapping("/getbytoken")
    public ResponseEntity<ResponseDTO> getPerson(@RequestParam String token) {

        Person person = service.selectPerson(token);
        ResponseDTO responseDTO = new ResponseDTO("Person '" + person.getId() + "' Profile: ", person, token);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle put request.
    @PutMapping("/put")
    public ResponseEntity<ResponseDTO> putPerson(@Valid @RequestParam int id, @RequestBody PersonDTO personDTO) {
        Person person = service.updatePerson(id, personDTO);

        // Adding an updated person to list.
        persons.add(person);

        ResponseDTO responseDTO = new ResponseDTO("Person details updated successfully!", person, service.getToken(id));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    // Api to handle put request by token.
    // Overloaded Method.
    @PutMapping("/putbytoken")
    public ResponseEntity<ResponseDTO> putPerson(@Valid @RequestParam String token, @RequestBody PersonDTO personDTO) {
        Person person = service.updatePerson(token, personDTO);

        // Adding an updated person to list.
        persons.add(person);

        ResponseDTO responseDTO = new ResponseDTO("Person details updated successfully!", person, token);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    // Api to handle delete request.
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deletePerson(@RequestParam int id) {
        Person person = service.deletePerson(id);

        // Adding an deleted person to a list.
        persons.add(person);

        ResponseDTO responseDTO = new ResponseDTO("Person '" + id + "' contact deleted successfully!", person, service.getToken(id));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle delete request by Person token.
    @DeleteMapping("/deletebytoken")
    public ResponseEntity<ResponseDTO> deletePerson(@RequestParam String token) {
        Person person = service.deletePerson(token);

        // Adding an deleted person to a list.
        persons.add(person);

        ResponseDTO responseDTO = new ResponseDTO("Person '" + person.getId() + "' contact deleted successfully!", person, token);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to handle get request for all Persons.
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllPerson() {
        List<Person> persons = service.selectAllPerson();
        ResponseDTO responseDTO = new ResponseDTO("Profile of all Persons: ", persons, null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //    Api to get person by name.
    @GetMapping("/getbyname")
    public ResponseEntity<ResponseDTO> getPersonByName(@RequestParam String fname, String lname) {
        List<Person> persons = service.findPersonByName(fname, lname);
        ResponseDTO responseDTO = new ResponseDTO("Profile of 'Persons' having same name: ", persons, null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Api to send mail.
    @PostMapping("/sendmail")
    public Email sendMyMail(@RequestBody Email email) {
        emailService.sendMail(email);
        return email;
    }
}