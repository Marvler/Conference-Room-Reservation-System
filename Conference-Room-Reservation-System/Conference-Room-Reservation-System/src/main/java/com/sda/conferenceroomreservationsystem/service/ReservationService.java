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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConferenceRoomService conferenceRoomService;
    private final ReservationRepository reservationRepository;

    public List<ReservationDto> getAll(Long id){
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(id);
        return reservationRepository.findByConferenceRoom(conferenceRoom).stream()
                .map(ReservationMapper::mapToDto)
                .sorted(Comparator.comparing(ReservationDto::getReservationStart)).toList();
    }

    public ReservationDto getReservation(Long id) {
        return ReservationMapper.mapToDto(getReservationFromDatabaseById(id));
    }

    public ReservationDto add(Long conferenceRoomId, final ReservationRequest request) {
        ConferenceRoom conferenceRoom = conferenceRoomService.getConferenceRoomFromDatabaseById(conferenceRoomId);
        final Reservation reservation = ReservationMapper.mapToEntity(conferenceRoom, request);
        return ReservationMapper.mapToDto(reservationRepository.save(reservation));
    }

    public ReservationDto update(Long id, final ReservationRequest request) {
        final Reservation reservationFromDb = getReservationFromDatabaseById(id);
        final Reservation reservationFromRequest = ReservationMapper.mapToEntity(request);
        reservationFromRequest.setReservationId(reservationFromDb.getReservationId());
        reservationFromRequest.setConferenceRoom(reservationFromDb.getConferenceRoom());

        return ReservationMapper.mapToDto(reservationRepository.save(reservationFromRequest));
    }

    public void deleteReservationById(Long id) {
        reservationRepository.delete(getReservationFromDatabaseById(id));
    }

    private Reservation getReservationFromDatabaseById(final Long reservationId) {
        final Optional<Reservation> reservationFromDatabase = reservationRepository.findById(reservationId);
        return reservationFromDatabase.orElseThrow(ReservationNotFoundException::new);
    }
}
