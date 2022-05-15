package com.sda.conferenceroomreservationsystem.exception;

public class OrganizationAlreadyExistsException extends Exception{

    public OrganizationAlreadyExistsException() {
        super("Given organization already exists");
    }
}
