package com.book.address.exception;

// Defining custom exception class.
public class AddrBookException extends RuntimeException {

    public AddrBookException(String message) {
        super(message);
    }
}
