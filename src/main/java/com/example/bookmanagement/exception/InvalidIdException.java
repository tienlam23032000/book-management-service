package com.example.bookmanagement.exception;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String errorMessage) {
        super(errorMessage);
    }
}
