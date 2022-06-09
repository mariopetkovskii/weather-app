package com.example.weatherapplication.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException{
    public InvalidUserCredentialException() {
        super(String.format("Invalid user credentials!"));
    }
}
