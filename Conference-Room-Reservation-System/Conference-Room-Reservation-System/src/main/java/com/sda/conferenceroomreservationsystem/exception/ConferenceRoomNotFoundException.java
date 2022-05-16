package com.sda.conferenceroomreservationsystem.exception;

public class ConferenceRoomNotFoundException extends RuntimeException{
    public ConferenceRoomNotFoundException() {
        super("Conference room not found");
    }
}
