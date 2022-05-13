package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.type.OrganizationNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.OrganizationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public List<OrganizationDto> getAll() {
        //TODO
        return null;
    }

    public OrganizationDto getOrganization(String name) {
        return organizationMapper.map(getOrganizationFromDatabase(name));
    }

    public Organization add(final OrganizationRequest request) {
        final Organization organization = organizationMapper.map(request);
        return organizationRepository.save(organization);
    }

    public OrganizationDto update(String name, final OrganizationRequest request) {
        final Organization organizationFromDb = getOrganizationFromDatabase(name);
        final Organization organizationFromRequest = organizationMapper.map(request);
        organizationFromRequest.setOrganizationId(organizationFromDb.getOrganizationId());

        return organizationMapper.map(organizationRepository.save(organizationFromRequest));
    }

    public void delete(String name) {
        organizationRepository.delete(getOrganizationFromDatabase(name));
    }

    public Organization getOrganizationFromDatabase(String name) {
        final Optional<Organization> organization = organizationRepository.findByOrganizationName(name);
        return organization.orElseThrow(OrganizationNotFoundException::new);
    }

    public Organization getOrganizationFromDatabaseWithAuth(String name, String password) {
        final Optional<Organization> organization = organizationRepository.findByOrganizationNameAndPassword(name, password);
        return organization.orElseThrow(OrganizationNotFoundException::new);
    }
}