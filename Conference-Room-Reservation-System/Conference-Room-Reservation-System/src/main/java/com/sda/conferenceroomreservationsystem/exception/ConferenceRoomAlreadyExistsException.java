package com.sda.conferenceroomreservationsystem.exception;

public class ConferenceRoomAlreadyExistsException extends RuntimeException{

    public ConferenceRoomAlreadyExistsException() {
        super("Conference room not found");
    }
}
