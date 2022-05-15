package com.sda.conferenceroomreservationsystem.exception;

public class ReservationAlreadyExistException extends Exception{
    public ReservationAlreadyExistException(){
        super("Reservation already exists");
    }
}
