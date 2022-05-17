package com.sda.conferenceroomreservationsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor(staticName = "of")
public class ConferenceRoomRequest {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 20, message="must be between 2-20 chars long")
    private String conferenceRoomName;
    @NotNull
    private String conferenceRoomIdentifier;
    private Integer level;
    private Integer numberOfSeats;
    private Integer numberOfStandings;
    @NotNull
    private Long organizationId;
}
