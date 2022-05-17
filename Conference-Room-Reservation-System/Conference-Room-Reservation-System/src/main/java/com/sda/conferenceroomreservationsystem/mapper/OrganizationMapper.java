package com.sda.conferenceroomreservationsystem.mapper;

import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@RequiredArgsConstructor
public class OrganizationMapper {

    private final PasswordEncoder passwordEncoder;

    public Organization mapToEntity(final OrganizationRequest request) {
        final Organization organization = new Organization();
        organization.setOrganizationName(request.getOrganizationName());
        organization.setPassword(passwordEncoder.encode(request.getPassword()));
        organization.setEmail(request.getEmail());
        organization.setConferenceRooms(new ArrayList<>());

        return organization;
    }

    public OrganizationDto mapToDto(final Organization organization) {
        return OrganizationDto.Builder()
                .withOrganizationId(organization.getOrganizationId())
                .withOrganizationName(organization.getOrganizationName())
                .withEmail(organization.getEmail())
                .withConferenceRooms(organization.getConferenceRooms().stream()
                        .map(ConferenceRoomMapper::mapToDto).toList())
                .build();
    }
}
