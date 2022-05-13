package com.sda.conferenceroomreservationsystem.model.dto;

import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import lombok.*;

import java.util.List;

@Getter
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganizationDto {
    private String organizationName;
    private String email;
    private List<ConferenceRoom> conferenceRooms;
}
