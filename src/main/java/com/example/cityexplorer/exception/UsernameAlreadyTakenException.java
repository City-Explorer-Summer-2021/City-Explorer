package com.example.cityexplorer.exception;

public class UsernameAlreadyTakenException extends RuntimeException {

    public UsernameAlreadyTakenException(String errorMessage) {
        super(errorMessage);
    }

}
