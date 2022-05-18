package com.sda.conferenceroomreservationsystem.exception.errorhandling;

import com.sda.conferenceroomreservationsystem.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandling {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrganizationNotFoundException.class)
    public Error handleOrganizationNotFound(final OrganizationNotFoundException exception) {
        return new Error(exception.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrganizationAlreadyExistsException.class)
    public Error handleOrganizationAlreadyExist(final OrganizationAlreadyExistsException exception) {
        return new Error(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConferenceRoomNotFoundException.class)
    public Error handleConferenceRoomNotFound(final ConferenceRoomNotFoundException exception) {
        return new Error(exception.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConferenceRoomAlreadyExistsException.class)
    public Error handleConferenceRoomAlreadyExist(final ConferenceRoomAlreadyExistsException exception) {
        return new Error(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public Error handleReservationNotFound(final ReservationNotFoundException exception) {
        return new Error(exception.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyExistException.class)
    public Error handleReservationAlreadyExist(final ReservationAlreadyExistException exception) {
        return new Error(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationCollidesException.class)
    public Error handleReservationCollision(final ReservationCollidesException exception) {
        return new Error(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }
}