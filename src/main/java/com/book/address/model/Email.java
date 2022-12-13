package com.book.address.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Just using lombok for boiler plate code of parameterized constructor & getters.
@AllArgsConstructor
@Getter
public class Email {

    private String to;

    private String subject, body;

}
