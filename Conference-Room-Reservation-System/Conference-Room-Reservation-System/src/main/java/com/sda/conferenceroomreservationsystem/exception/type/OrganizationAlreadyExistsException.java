package com.sda.conferenceroomreservationsystem.exception.type;

public class OrganizationAlreadyExistsException extends Exception{

    public OrganizationAlreadyExistsException() {
        super("Given organization already exists");
    }
}
