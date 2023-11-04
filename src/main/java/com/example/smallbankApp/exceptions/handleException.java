package com.example.smallbankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class handleException{
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<String> emailExists(EmailExistsException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(PhoneNumberExistsException.class)
    public ResponseEntity<String> phoneExists(PhoneNumberExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> insufficientBalance(InsufficientBalanceException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> accountNotFound(AccountNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }
}
