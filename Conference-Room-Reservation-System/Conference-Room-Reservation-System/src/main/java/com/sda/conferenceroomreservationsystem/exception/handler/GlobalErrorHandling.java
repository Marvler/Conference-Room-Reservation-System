package com.sda.conferenceroomreservationsystem.exception.handler;

import com.sda.conferenceroomreservationsystem.exception.type.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandling {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrganizationNotFoundException.class)
    public java.lang.Error handleOrganizationNotFound(final OrganizationNotFoundException exception) {
        return new java.lang.Error(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrganizationAlreadyExistsException.class)
    public java.lang.Error handleOrganizationAlreadyExist(final OrganizationAlreadyExistsException exception) {
        return new java.lang.Error(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConferenceRoomNotFoundException.class)
    public java.lang.Error handleConferenceRoomNotFound(final ConferenceRoomNotFoundException exception) {
        return new java.lang.Error(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConferenceRoomAlreadyExistsException.class)
    public java.lang.Error handleConferenceRoomAlreadyExist(final ConferenceRoomAlreadyExistsException exception) {
        return new java.lang.Error(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public java.lang.Error handleReservationNotFound(final ReservationNotFoundException exception) {
        return new java.lang.Error(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyExistException.class)
    public java.lang.Error handleReservationAlreadyExist(final ReservationAlreadyExistException exception) {
        return new java.lang.Error(exception.getMessage());
    }
}