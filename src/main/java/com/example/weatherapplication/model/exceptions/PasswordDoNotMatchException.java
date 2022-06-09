package com.example.weatherapplication.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException(String message) {
        super(message);
    }
}
