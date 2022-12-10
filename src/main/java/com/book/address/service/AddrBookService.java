package com.book.address.service;

import com.book.address.dto.PersonDTO;
import com.book.address.exception.AddrBookException;
import com.book.address.model.Person;
import com.book.address.repository.IAddrBookRepo;
import com.book.address.util.PersonToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddrBookService implements IAddrBookService {

    @Autowired // Automatic Dependency Injection.
    IAddrBookRepo addrBookRepo;

    @Autowired
    PersonToken personToken;

    // Getting token from person id.
    public String getToken(int personId) {
        return personToken.createToken(personId);
    }

    // Inserting a new Person in repo.
    @Override
    public Person insertPerson(PersonDTO personDTO) {
        Person person = new Person(personDTO);

        log.info("Saving a new Person in repository!");
        return addrBookRepo.save(person);
    }

    // Selecting a Person by id from repo.
    @Override
    public Person selectPerson(int personId) {

        Optional<Person> optionalPerson = addrBookRepo.findById(personId);

        // Checking person with same id present or not.
        if (optionalPerson.isPresent())
            return  optionalPerson.get();
        // Throwing exception manually.
        throw new AddrBookException("Person with id = '" + personId + "' not exists in repository!");
    }

    // Updating an existing Person in repo.
    @Override
    public Person updatePerson(int personId, PersonDTO personDTO) {

        // Checking person with 'personId' is there in repository or not.
        if (addrBookRepo.findById(personId).isPresent()) {
            Person person = new Person(personDTO);
            person.setId(personId);
        return addrBookRepo.save(person);
        }
        throw new AddrBookException("Can't Update: Person with id = '" + "' does not exist in repository!");
    }

    // Deleting a Person record from repo.
    @Override
    public Person deletePerson(int personId) {
        Optional<Person> personOptional = addrBookRepo.findById(personId);
        addrBookRepo.deleteById(personId);

        // Returning deleted Person.
        return personOptional.get();
    }

    // Getting all Person details from repo.
    @Override
    public List<Person> selectAllPerson() {
        return addrBookRepo.findAll();
    }

    // Getting Persons having same name.
    @Override
    public List<Person> findPersonByName(String fname, String lname) {
        return addrBookRepo.findByPersonName(fname, lname);
    }

    // Getting Id from token.
    private int getIdFromToken(String token) {
        return personToken.decodeToken(token);
    }

    // Getting a Person by token.
    @Override // Method overloading works here.
    public Person selectPerson(String token) {
        return selectPerson(getIdFromToken(token));
    }

    @Override
    public Person updatePerson(String token, PersonDTO personDTO) {
        return updatePerson(getIdFromToken(token), personDTO);
    }

    @Override
    public Person deletePerson(String token) {
        return deletePerson(getIdFromToken(token));
    }
}