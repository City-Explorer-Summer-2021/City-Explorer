package com.example.cityexplorer.exception;

public class BadLoginPasswordException extends RuntimeException {
    public BadLoginPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
