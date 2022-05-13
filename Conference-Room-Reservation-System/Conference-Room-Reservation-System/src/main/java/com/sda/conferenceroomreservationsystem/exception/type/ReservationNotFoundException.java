package com.sda.conferenceroomreservationsystem.exception.type;

public class ReservationNotFoundException extends Exception{
    public ReservationNotFoundException() {
        super("Reservation not found");
    }
}
