package com.example.eventplanner.exception;

public class SenderNotFoundInEventException extends RuntimeException{
    public SenderNotFoundInEventException(String message) {
        super(message);
    }
}
