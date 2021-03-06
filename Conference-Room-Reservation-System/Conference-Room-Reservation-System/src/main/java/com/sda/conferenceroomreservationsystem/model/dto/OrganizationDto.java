package com.sda.conferenceroomreservationsystem.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrganizationDto {
    private Long organizationId;
    private String organizationName;
    private String email;
    private List<ConferenceRoomDto> conferenceRooms;
}
