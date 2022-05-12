package com.sda.testreservationsystem.service;

import com.sda.testreservationsystem.repository.ConferenceRoomRepository;
import com.sda.testreservationsystem.repository.OrganizationRepository;
import com.sda.testreservationsystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationRepository organizationRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ConferenceRoomRepository conferenceRoomRepository,
                              OrganizationRepository organizationRepository,
                              ReservationRepository reservationRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationRepository;
        this.reservationRepository = reservationRepository;
    }
}
