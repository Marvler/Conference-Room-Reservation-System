package com.sda.conferenceroomreservationsystem.mapper;

import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomMapper {
    public ConferenceRoom map(final ConferenceRoomRequest request) {
        final ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomName(request.getConferenceRoomName());
        conferenceRoom.setConferenceRoomIdentifier(request.getConferenceRoomIdentifier());
        conferenceRoom.setLevel(request.getLevel());
        conferenceRoom.setNumberOfSeats(request.getNumberOfSeats());
        conferenceRoom.setNumberOfStandings(request.getNumberOfStandings());

        return conferenceRoom;
    }

    public static ConferenceRoomDto map(final ConferenceRoom conferenceRoom) {
        return ConferenceRoomDto.Builder()
                .withConferenceRoomName(conferenceRoom.getConferenceRoomName())
                .withConferenceRoomIdentifier(conferenceRoom.getConferenceRoomIdentifier())
                .withLevel(conferenceRoom.getLevel())
                .withAvailability(conferenceRoom.getAvailability())
                .withNumberOfSeats(conferenceRoom.getNumberOfSeats())
                .withNumberOfStandings(conferenceRoom.getNumberOfStandings())
                .withReservations(conferenceRoom.getReservations())
                .build();
    }
}
