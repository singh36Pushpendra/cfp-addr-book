package com.book.address.repository;

import com.book.address.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAddrBookRepo extends JpaRepository<Person, Integer> {
    @Query(value = "select * from Person where fname = :firstName and lname = :lastName", nativeQuery = true)
    List<Person> findByPersonName(String firstName, String lastName);
}
