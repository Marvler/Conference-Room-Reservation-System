package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.type.ReservationNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.ReservationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import com.sda.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import com.sda.conferenceroomreservationsystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationRepository organizationRepository;
    private final ReservationRepository reservationRepository;

    public List<ReservationDto> getAllReservations(){
        final List<Reservation> reservations = reservationRepository.findAll();
        return null;
    }

    public ReservationDto getReservationByName(String name) {
        //TODO
        //return ReservationMapper.map(reservationRepository.findByName(name));
        return null;
    }

    public ReservationDto updateReservation(Long id, Reservation reservation) {
        //TODO
        return null;
    }

    public ReservationDto createReservation(Reservation reservation) {
        //TODO
        return null;
    }

    public void deleteReservationById(Long id)
            throws ReservationNotFoundException {
        //TODO
        reservationRepository.delete(getReservationFromDatabaseById(id));
    }

    private Reservation getReservationFromDatabaseById(final Long reservationId) throws ReservationNotFoundException {
        final Optional<Reservation> reservationFromDatabase = reservationRepository.findById(reservationId);
        return reservationFromDatabase.orElseThrow(ReservationNotFoundException::new);
    }
}
