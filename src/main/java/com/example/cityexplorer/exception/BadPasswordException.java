package com.example.cityexplorer.exception;

public class BadPasswordException extends RuntimeException {

    public BadPasswordException(String errorMessage) {
        super(errorMessage);
    }

}
