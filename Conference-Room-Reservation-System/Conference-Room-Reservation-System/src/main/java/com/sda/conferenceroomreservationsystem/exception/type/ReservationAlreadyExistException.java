package com.sda.conferenceroomreservationsystem.exception.type;

public class ReservationAlreadyExistException extends Exception{
    public ReservationAlreadyExistException(){
        super("Reservation already exists");
    }
}
