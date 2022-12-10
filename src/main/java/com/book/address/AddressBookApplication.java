package com.book.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AddressBookApplication {

	public static void main(String[] args)
	{
		// Just to print on console.
		log.info("Before starting Spring Boot application!");
		SpringApplication.run(AddressBookApplication.class, args);
		log.info("After starting Spring Boot application!");
	}

}
