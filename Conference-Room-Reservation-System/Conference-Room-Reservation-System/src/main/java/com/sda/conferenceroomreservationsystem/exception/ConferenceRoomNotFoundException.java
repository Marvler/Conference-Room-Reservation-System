package com.sda.conferenceroomreservationsystem.exception;

public class ConferenceRoomNotFoundException extends RuntimeException{
    public ConferenceRoomNotFoundException() {
        super("Organization not found");
    }
}
