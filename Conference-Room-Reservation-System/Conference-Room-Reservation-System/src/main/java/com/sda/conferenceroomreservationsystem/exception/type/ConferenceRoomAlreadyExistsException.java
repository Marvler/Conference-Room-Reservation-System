package com.sda.conferenceroomreservationsystem.exception.type;

public class ConferenceRoomAlreadyExistsException extends Exception{

    public ConferenceRoomAlreadyExistsException() {
        super("Conference room not found");
    }
}
