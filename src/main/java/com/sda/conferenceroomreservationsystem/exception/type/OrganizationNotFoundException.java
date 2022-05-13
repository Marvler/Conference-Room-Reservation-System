package com.sda.conferenceroomreservationsystem.exception.type;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException() {
        super("Organization not found!");
    }
}
