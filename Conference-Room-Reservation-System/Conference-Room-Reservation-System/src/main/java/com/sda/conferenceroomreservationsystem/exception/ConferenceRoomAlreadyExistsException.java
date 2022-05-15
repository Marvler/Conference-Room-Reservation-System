package com.sda.conferenceroomreservationsystem.exception;

public class ConferenceRoomAlreadyExistsException extends Exception{

    public ConferenceRoomAlreadyExistsException() {
        super("Conference room not found");
    }
}
