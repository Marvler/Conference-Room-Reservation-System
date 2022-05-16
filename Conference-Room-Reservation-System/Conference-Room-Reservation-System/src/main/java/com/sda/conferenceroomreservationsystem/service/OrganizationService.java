package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.OrganizationNotFoundException;
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

    public List<OrganizationDto> getAll() {
        //TODO
        return null;
    }

    public OrganizationDto getOrganization(Long id) {
        return OrganizationMapper.mapToDto(getOrganizationFromDatabase(id));
    }

    public OrganizationDto add(final OrganizationRequest request) {
        final Organization organization = OrganizationMapper.mapToEntity(request);
        return OrganizationMapper.mapToDto(organizationRepository.save(organization));
    }

    public OrganizationDto update(Long id, final OrganizationRequest request) {
        final Organization organizationFromDb = getOrganizationFromDatabase(id);
        final Organization organizationFromRequest = OrganizationMapper.mapToEntity(request);
        organizationFromRequest.setOrganizationId(organizationFromDb.getOrganizationId());
        organizationFromRequest.setConferenceRooms(organizationFromDb.getConferenceRooms());

        return OrganizationMapper.mapToDto(organizationRepository.save(organizationFromRequest));
    }

    public void delete(Long id) {
        organizationRepository.delete(getOrganizationFromDatabase(id));
    }

    public Organization getOrganizationFromDatabase(Long id) {
        final Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElseThrow(OrganizationNotFoundException::new);
    }

    public Organization getOrganizationFromDatabaseWithAuth(String name, String password) {
        final Optional<Organization> organization = organizationRepository.findByOrganizationNameAndPassword(name, password);
        return organization.orElseThrow(OrganizationNotFoundException::new);
    }
}
