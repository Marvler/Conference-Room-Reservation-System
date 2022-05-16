package com.sda.conferenceroomreservationsystem.exception;

public class ReservationAlreadyExistException extends RuntimeException{
    public ReservationAlreadyExistException(){
        super("Reservation already exists");
    }
}
