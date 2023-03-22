package com.example.eventplanner.exception;

public class GroupNotFoundException extends RuntimeException{

    public GroupNotFoundException(String message) {
        super(message);
    }
}
