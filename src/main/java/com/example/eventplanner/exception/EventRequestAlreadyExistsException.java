package com.example.eventplanner.exception;

public class EventRequestAlreadyExistsException extends RuntimeException {
    public EventRequestAlreadyExistsException(String message) {
        super(message);
    }
}
