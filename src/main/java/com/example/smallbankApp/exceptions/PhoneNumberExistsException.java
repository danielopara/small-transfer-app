package com.example.smallbankApp.exceptions;

public class PhoneNumberExistsException extends RuntimeException{
    public PhoneNumberExistsException(String message){
        super(message);
    }
}
