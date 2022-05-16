package com.sda.conferenceroomreservationsystem.exception;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException() {
        super("Organization not found!");
    }
}
