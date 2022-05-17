package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomNotFoundException;
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

        principalValidator(conferenceRoom.getOrganization(), principal);

        return reservationRepository.findByConferenceRoom(conferenceRoom).stream()
                .map(ReservationMapper::mapToDto)
                .sorted(Comparator.comparing(ReservationDto::getReservationStart)).toList();
    }

    public ReservationDto getReservation(Long id, String principal) {
        Reservation reservation = getReservationFromDatabaseById(id);

        principalValidator(reservation.getConferenceRoom().getOrganization(), principal);

        return ReservationMapper.mapToDto(reservation);
    }

    public ReservationDto add(final ReservationRequest request, String principal) {
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(request.getConferenceRoomId());

        principalValidator(conferenceRoom.getOrganization(), principal);

        if (request.isOccupied(conferenceRoom.getReservations())) {
            throw new ReservationCollidesException();
        }

        final Reservation reservation = generateReservationWithIdentifier(ReservationMapper.mapToEntity(conferenceRoom, request));
        try {
            reservationRepository.save(reservation);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationAlreadyExistException();
        }
        return ReservationMapper.mapToDto(reservation);
    }

    public ReservationDto update(Long id, final ReservationRequest request, String principal) {
        final Reservation reservationFromDb = getReservationFromDatabaseById(id);
        final Reservation reservationFromRequest = ReservationMapper.mapToEntity(request);

        principalValidator(reservationFromDb.getConferenceRoom().getOrganization(), principal);

        reservationFromRequest.setReservationId(reservationFromDb.getReservationId());
        reservationFromRequest.setConferenceRoom(reservationFromDb.getConferenceRoom());

        return ReservationMapper.mapToDto(reservationRepository.save(reservationFromRequest));
    }

    public void deleteReservationById(Long id, String principal) {
        Reservation reservation = getReservationFromDatabaseById(id);

        principalValidator(reservation.getConferenceRoom().getOrganization(), principal);

        reservationRepository.delete(reservation);
    }

    private Reservation getReservationFromDatabaseById(final Long reservationId) {
        final Optional<Reservation> reservationFromDatabase = reservationRepository.findById(reservationId);
        return reservationFromDatabase.orElseThrow(ReservationNotFoundException::new);
    }

    private Reservation generateReservationWithIdentifier(Reservation reservation) {
        Random random = new Random();

        ConferenceRoom conferenceRoom = reservation.getConferenceRoom();
        Organization organization = conferenceRoom.getOrganization();
        String organizationAbr = organization.getOrganizationName().substring(0,2).toUpperCase();
        String conferenceRoomAbr = conferenceRoom.getConferenceRoomName().substring(0,2).toUpperCase();
        String identifier = organizationAbr + "-" + conferenceRoomAbr + "-" + (random.nextInt(9000) + 1000);
        reservation.setReservationIdentifier(identifier);
        return reservation;
    }

    private void principalValidator(Organization organization, String name) {
        if (!organization.getOrganizationName().equals(name)) {
            throw new ReservationNotFoundException();
        }
    }
}
