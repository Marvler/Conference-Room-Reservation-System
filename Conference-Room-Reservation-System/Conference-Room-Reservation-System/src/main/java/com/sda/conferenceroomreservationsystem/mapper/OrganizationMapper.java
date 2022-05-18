package com.sda.conferenceroomreservationsystem.mapper;

import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {

    private final PasswordEncoder passwordEncoder;

    public Organization mapToEntity(final OrganizationRequest request) {
        return Organization.Builder()
                .withOrganizationName(request.getOrganizationName())
                .withPassword(passwordEncoder.encode(request.getPassword()))
                .withEmail(request.getEmail())
                .withConferenceRooms(new ArrayList<>())
                .build();
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
