package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ReservationNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.ReservationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import com.sda.conferenceroomreservationsystem.model.request.ReservationRequest;
import com.sda.conferenceroomreservationsystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConferenceRoomService conferenceRoomService;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public List<ReservationDto> getAll(){
        return reservationRepository.findAll().stream().map(ReservationMapper::map).collect(Collectors.toList());
    }

    public ReservationDto getReservation(Long id) throws ReservationNotFoundException {
        return reservationMapper.map(getReservationFromDatabaseById(id));
    }

    public Reservation add(Long conferenceRoomId, final ReservationRequest request) {
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(conferenceRoomId);
        final Reservation reservation = reservationMapper.map(conferenceRoom, request);
        return reservationRepository.save(reservation);
    }

    public ReservationDto update(Long id, final ReservationRequest request) throws ReservationNotFoundException {
        final Reservation reservationFromDb = getReservationFromDatabaseById(id);
        final Reservation reservationFromRequest = reservationMapper.map(request);
        reservationFromRequest.setReservationId(reservationFromDb.getReservationId());
        reservationFromRequest.setConferenceRoom(reservationFromDb.getConferenceRoom());

        return reservationMapper.map(reservationRepository.save(reservationFromRequest));
    }

    public void deleteReservationById(Long id) throws ReservationNotFoundException {
        reservationRepository.delete(getReservationFromDatabaseById(id));
    }

    private Reservation getReservationFromDatabaseById(final Long reservationId) throws ReservationNotFoundException {
        final Optional<Reservation> reservationFromDatabase = reservationRepository.findById(reservationId);
        return reservationFromDatabase.orElseThrow(ReservationNotFoundException::new);
    }
}
