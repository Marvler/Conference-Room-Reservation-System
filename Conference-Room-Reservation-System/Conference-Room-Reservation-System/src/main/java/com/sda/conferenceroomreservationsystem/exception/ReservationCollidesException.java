package com.sda.conferenceroomreservationsystem.exception;

public class ReservationCollidesException extends RuntimeException {
    public ReservationCollidesException() {
        super("Reservation collides with other reservations");
    }
}
