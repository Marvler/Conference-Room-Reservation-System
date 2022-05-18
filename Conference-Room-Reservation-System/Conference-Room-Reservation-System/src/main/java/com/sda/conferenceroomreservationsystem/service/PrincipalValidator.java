package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.exception.OrganizationNotFoundException;
import com.sda.conferenceroomreservationsystem.exception.ReservationNotFoundException;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;

public class PrincipalValidator {

    public static void validateOrganization(Organization organization, String name) {
        if (!organization.getOrganizationName().equals(name)) {
            throw new OrganizationNotFoundException();
        }
    }

    public static void validateConferenceRoom(Organization organization, String name) {
        if (!organization.getOrganizationName().equals(name)) {
            throw new ConferenceRoomNotFoundException();
        }
    }

    public static void validateReservation(Organization organization, String name) {
        if (!organization.getOrganizationName().equals(name)) {
            throw new ReservationNotFoundException();
        }
    }
}
