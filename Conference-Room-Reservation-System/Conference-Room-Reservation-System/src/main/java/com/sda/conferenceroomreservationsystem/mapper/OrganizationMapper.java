package com.sda.conferenceroomreservationsystem.mapper;

import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;

import java.util.ArrayList;

public class OrganizationMapper {

    public static Organization mapToEntity(final OrganizationRequest request) {
        final Organization organization = new Organization();
        organization.setOrganizationName(request.getOrganizationName());
        organization.setPassword(request.getPassword());
        organization.setEmail(request.getEmail());
        organization.setConferenceRooms(new ArrayList<>());

        return organization;
    }

    public static OrganizationDto mapToDto(final Organization organization) {
        return OrganizationDto.Builder()
                .withOrganizationName(organization.getOrganizationName())
                .withEmail(organization.getEmail())
                .withConferenceRooms(organization.getConferenceRooms().stream()
                        .map(ConferenceRoomMapper::mapToDto).toList())
                .build();
    }
}
