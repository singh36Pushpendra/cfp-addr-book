package com.book.address.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonDTO {

    // Validating name.
    @NotNull(message = "First or last name should not be null!")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Invalid first or last name!")
    public String fname, lname;

    // Exact 10 digit pattern for phone number.
    public long phoneNum;

    @NotNull(message = "Address, city or state should not be null!")
    public String address, city, state;

    // Exact 6 digit pattern for zip.
    public int zipCode;

}
