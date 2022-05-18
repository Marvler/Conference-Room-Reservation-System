package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;

import java.util.Random;

public class ReservationIdentifierGenerator {

    public static Reservation generate(Reservation reservation) {
        Random random = new Random();

        ConferenceRoom conferenceRoom = reservation.getConferenceRoom();
        Organization organization = conferenceRoom.getOrganization();
        String organizationAbr = organization.getOrganizationName().substring(0,2).toUpperCase();
        String conferenceRoomAbr = conferenceRoom.getConferenceRoomName().substring(0,2).toUpperCase();
        String identifier = organizationAbr + "-" + conferenceRoomAbr + "-" + (random.nextInt(9000) + 1000);
        reservation.setReservationIdentifier(identifier);
        return reservation;
    }
}
