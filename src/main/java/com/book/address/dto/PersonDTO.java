package com.book.address.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonDTO {

    // Validating name.
    @NotNull(message = "First or last name should not be null!")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Invalid first or last name!")
    public String fname, lname;

//     Exact 10 digit for phone number.
    @Size(min = 10, max = 10, message = "Phone number should have exactly 10 digit!")
    public String phoneNum;

    @Email(regexp = "^[a-zA-Z]+([.+-]?[a-zA-Z0-9]{1,})*[@][a-zA-Z0-9]+[.][a-zA-Z]{2,3}" +
            "([.][a-zA-Z]{2,3})?[,]?$", message = "Invalid email value!")
    public String email;

    @NotNull(message = "Address, city or state should not be null!")
    public String address, city, state;

//     Exact 6 digit for zip code.
    @Size(min = 6, max = 6, message = "Zip code should have exactly 6 digit!")
    public String zipCode;

}
