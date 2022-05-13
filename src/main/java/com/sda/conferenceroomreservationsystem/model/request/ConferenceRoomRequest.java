package com.sda.conferenceroomreservationsystem.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConferenceRoomRequest {
    @NotNull
    private String conferenceRoomName;
    @NotNull
    private String conferenceRoomIdentifier;
    private Integer level;
    private Integer numberOfSeats;
    private Integer numberOfStandings;
}
