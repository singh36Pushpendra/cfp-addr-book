package com.book.address.exception;

import com.book.address.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

// Allows us to handle multiple exception.
@ControllerAdvice
public class AddrBookExceptionHandler {

    // Defining a final message.
    private static final String MSG = "Exception while processing rest request!";

    // Allows us to handle specified exceptions.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgNotValidException(MethodArgumentNotValidException exception) {

        // Getting a list of ObjectError from exception.
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

        // Applying stream API to convert list of ObjectError into list of String.
        List<String> errMsg = allErrors.stream()
                .map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());

        ResponseDTO responseDTO = new ResponseDTO(MSG, errMsg);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }

    // Handling custom exception.
    @ExceptionHandler(AddrBookException.class)
    public ResponseEntity<ResponseDTO> handleAddrBookException(AddrBookException exception) {
        ResponseDTO responseDTO = new ResponseDTO(MSG, exception.getMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
