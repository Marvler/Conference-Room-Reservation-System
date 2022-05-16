package com.sda.conferenceroomreservationsystem.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException() {
        super("Reservation not found");
    }
}
