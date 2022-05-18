package com.sda.conferenceroomreservationsystem.mapper;

import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;

import java.util.ArrayList;

public class ConferenceRoomMapper {
    public static ConferenceRoom mapToEntity(final Organization organization, final ConferenceRoomRequest request) {
        return ConferenceRoom.Builder()
                .withConferenceRoomName(request.getConferenceRoomName())
                .withConferenceRoomIdentifier(request.getConferenceRoomIdentifier())
                .withLevel(request.getLevel())
                .withNumberOfSeats(request.getNumberOfSeats())
                .withNumberOfStandings(request.getNumberOfStandings())
                .withOrganization(organization)
                .withReservations(new ArrayList<>())
                .build();
    }

    public static ConferenceRoom mapToEntity(final ConferenceRoomRequest request) {
        return ConferenceRoom.Builder()
                .withConferenceRoomName(request.getConferenceRoomName())
                .withConferenceRoomIdentifier(request.getConferenceRoomIdentifier())
                .withLevel(request.getLevel())
                .withNumberOfSeats(request.getNumberOfSeats())
                .withNumberOfStandings(request.getNumberOfStandings())
                .build();
    }

    public static ConferenceRoomDto mapToDto(final ConferenceRoom conferenceRoom) {
        return ConferenceRoomDto.Builder()
                .withConferenceRoomId(conferenceRoom.getConferenceRoomId())
                .withConferenceRoomName(conferenceRoom.getConferenceRoomName())
                .withConferenceRoomIdentifier(conferenceRoom.getConferenceRoomIdentifier())
                .withLevel(conferenceRoom.getLevel())
                .withAvailability(conferenceRoom.isAvailable())
                .withNumberOfSeats(conferenceRoom.getNumberOfSeats())
                .withNumberOfStandings(conferenceRoom.getNumberOfStandings())
                .withReservations(conferenceRoom.getReservations().stream().map(ReservationMapper::mapToDto).toList())
                .build();
    }
}
