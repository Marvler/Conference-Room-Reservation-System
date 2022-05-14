package com.sda.conferenceroomreservationsystem.exception;

public class ReservationNotFoundException extends Exception{
    public ReservationNotFoundException() {
        super("Reservation not found");
    }
}
