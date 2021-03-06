package com.sda.conferenceroomreservationsystem.mapper;

import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import com.sda.conferenceroomreservationsystem.model.request.ReservationRequest;

public class ReservationMapper {
    public static Reservation mapToEntity(final ConferenceRoom conferenceRoom, final ReservationRequest request) {
        return Reservation.Builder()
                .withReservationStart(request.getReservationStart())
                .withReservationEnd(request.getReservationEnd())
                .withConferenceRoom(conferenceRoom)
                .build();
    }

    public static ReservationDto mapToDto(final Reservation reservation) {
        return ReservationDto.Builder()
                .withReservationId(reservation.getReservationId())
                .withReservationIdentifier(reservation.getReservationIdentifier())
                .withReservationStart(reservation.getReservationStart())
                .withReservationEnd(reservation.getReservationEnd())
                .withConferenceRoomId(reservation.getConferenceRoom().getConferenceRoomId())
                .build();
    }
}
