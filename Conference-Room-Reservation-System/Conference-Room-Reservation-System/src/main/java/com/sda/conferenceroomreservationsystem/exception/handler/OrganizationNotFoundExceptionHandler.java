package com.sda.conferenceroomreservationsystem.exception.handler;

import com.sda.conferenceroomreservationsystem.exception.type.OrganizationNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrganizationNotFoundExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public Error handleOrganizationNotFoundException(final OrganizationNotFoundException exception) {
        return new Error(exception.getMessage());
    }
}
