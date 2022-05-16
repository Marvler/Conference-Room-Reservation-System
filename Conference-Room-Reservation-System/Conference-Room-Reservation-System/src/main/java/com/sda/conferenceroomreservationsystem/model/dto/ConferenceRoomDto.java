package com.sda.conferenceroomreservationsystem.model.dto;

import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import lombok.*;

import java.util.List;

@Getter
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ConferenceRoomDto {
    private String conferenceRoomName;
    private String conferenceRoomIdentifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;
    private Integer numberOfStandings;
    private List<ReservationDto> reservations;
}
