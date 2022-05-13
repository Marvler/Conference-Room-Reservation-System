package com.sda.conferenceroomreservationsystem.exception.type;

public class ConferenceRoomNotFoundException extends Exception{
    public ConferenceRoomNotFoundException() {
        super("Organization not found");
    }
}
