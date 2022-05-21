package com.sda.conferenceroomreservationsystem.exception;

public class ReservationAlreadyExistException extends RuntimeException{
    public ReservationAlreadyExistException(){
        super("Sorry, but this reservation already exists, it is because of generated identifier. Please, try again");
    }
}
