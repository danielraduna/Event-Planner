package com.example.eventplanner.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException){
        return ResponseEntity
                .badRequest()
                .body(userNotFoundException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<String> userAlreadyExists(UserAlreadyExistsException userAlreadyExistsException){
        return ResponseEntity
                .badRequest()
                .body(userAlreadyExistsException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EventNotFoundException.class})
    public ResponseEntity<String> eventNotFound(EventNotFoundException eventNotFoundException){
        return ResponseEntity
                .badRequest()
                .body(eventNotFoundException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {GroupNotFoundException.class})
    public ResponseEntity<String> groupNotFound(GroupNotFoundException groupNotFoundException){
        return ResponseEntity
                .badRequest()
                .body(groupNotFoundException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {SenderNotFoundInEventException.class})
    public ResponseEntity<String> senderNotFoundInEvent(SenderNotFoundInEventException senderNotFoundInEventException){
        return ResponseEntity
                .badRequest()
                .body(senderNotFoundInEventException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EventRequestAlreadyExistsException.class})
    public ResponseEntity<String> eventRequestAlreadyExists(EventRequestAlreadyExistsException eventRequestAlreadyExistsException){
        return ResponseEntity
                .badRequest()
                .body(eventRequestAlreadyExistsException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<String> userParametersValidation(MethodArgumentNotValidException badRequestException){

        String error = ((BeanPropertyBindingResult) badRequestException.getBindingResult())
                .getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return ResponseEntity
                .badRequest()
                .body(error);
    }
}
