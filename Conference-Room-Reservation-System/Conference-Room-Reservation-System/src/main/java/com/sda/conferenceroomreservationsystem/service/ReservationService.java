package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ReservationAlreadyExistException;
import com.sda.conferenceroomreservationsystem.exception.ReservationCollidesException;
import com.sda.conferenceroomreservationsystem.exception.ReservationNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.ReservationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import com.sda.conferenceroomreservationsystem.model.request.ReservationRequest;
import com.sda.conferenceroomreservationsystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConferenceRoomService conferenceRoomService;
    private final ReservationRepository reservationRepository;

    public List<ReservationDto> getAll(Long id, String principal){
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(id);

        PrincipalValidator.validateConferenceRoom(conferenceRoom.getOrganization(), principal);

        return reservationRepository.findByConferenceRoom(conferenceRoom).stream()
                .map(ReservationMapper::mapToDto)
                .sorted(Comparator.comparing(ReservationDto::getReservationStart)).toList();
    }

    public ReservationDto getReservation(Long id, String principal) {
        Reservation reservation = getReservationFromDatabaseById(id);

        PrincipalValidator.validateReservation(reservation.getConferenceRoom().getOrganization(), principal);

        return ReservationMapper.mapToDto(reservation);
    }

    public ReservationDto add(final ReservationRequest request, String principal) {
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(request.getConferenceRoomId());

        PrincipalValidator.validateConferenceRoom(conferenceRoom.getOrganization(), principal);

        if (request.isOccupied(conferenceRoom.getReservations())) {
            throw new ReservationCollidesException();
        }

        final Reservation reservation = ReservationIdentifierGenerator.generate(ReservationMapper.mapToEntity(conferenceRoom, request));
        try {
            reservationRepository.save(reservation);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationAlreadyExistException();
        }
        return ReservationMapper.mapToDto(reservation);
    }

    @Transactional
    public ReservationDto update(Long id, final ReservationRequest request, String principal) {
        final ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(request.getConferenceRoomId());

        if (request.isOccupied(conferenceRoom.getReservations())) {
            throw new ReservationCollidesException();
        }

        final Reservation reservationFromDb = getReservationFromDatabaseById(id);
        final Reservation reservationFromRequest = ReservationMapper.mapToEntity(conferenceRoom, request);

        PrincipalValidator.validateReservation(reservationFromDb.getConferenceRoom().getOrganization(), principal);
        PrincipalValidator.validateConferenceRoom(conferenceRoom.getOrganization(), principal);

        reservationFromRequest.setReservationId(reservationFromDb.getReservationId());
        reservationFromRequest.setReservationIdentifier(reservationFromDb.getReservationIdentifier());

        return ReservationMapper.mapToDto(reservationRepository.save(reservationFromRequest));
    }

    public void deleteReservationById(Long id, String principal) {
        Reservation reservation = getReservationFromDatabaseById(id);

        PrincipalValidator.validateReservation(reservation.getConferenceRoom().getOrganization(), principal);

        reservationRepository.delete(reservation);
    }

    private Reservation getReservationFromDatabaseById(final Long reservationId) {
        final Optional<Reservation> reservationFromDatabase = reservationRepository.findById(reservationId);
        return reservationFromDatabase.orElseThrow(ReservationNotFoundException::new);
    }
}
