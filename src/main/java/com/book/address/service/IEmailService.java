package com.book.address.service;

import com.book.address.dto.ResponseDTO;
import com.book.address.model.Email;
import org.springframework.http.ResponseEntity;

public interface IEmailService {

    // Abstract method to send mail to others.
    void sendMail(Email email);
}
