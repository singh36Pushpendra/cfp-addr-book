package com.book.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Providing boiler plate code for getter methods and parameterized constructor.
@AllArgsConstructor
@Getter
public class ResponseDTO {
    private String message;
    private Object data;
}
