package com.sda.conferenceroomreservationsystem.exception;

public class ConferenceRoomNotFoundException extends Exception{
    public ConferenceRoomNotFoundException() {
        super("Organization not found");
    }
}
