package com.sda.conferenceroomreservationsystem.exception;

public class ConferenceRoomIdentifierAlreadyExistsException extends RuntimeException {

    public ConferenceRoomIdentifierAlreadyExistsException() {
        super("Conference room identifier already exists");
    }
}
