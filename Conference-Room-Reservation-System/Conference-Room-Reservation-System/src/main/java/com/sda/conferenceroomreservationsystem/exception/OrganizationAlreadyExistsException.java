package com.sda.conferenceroomreservationsystem.exception;

public class OrganizationAlreadyExistsException extends RuntimeException{

    public OrganizationAlreadyExistsException() {
        super("Organization already exists");
    }
}
