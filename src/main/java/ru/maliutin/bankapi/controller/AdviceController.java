package ru.maliutin.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.maliutin.bankapi.exception.ClientUpdateException;
import ru.maliutin.bankapi.exception.ExceptionBody;
import ru.maliutin.bankapi.exception.ResourceNotFoundException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(ResourceNotFoundException e){
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(ClientUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleClientUpdate(ClientUpdateException e){
        return new ExceptionBody(e.getMessage());
    }

}
