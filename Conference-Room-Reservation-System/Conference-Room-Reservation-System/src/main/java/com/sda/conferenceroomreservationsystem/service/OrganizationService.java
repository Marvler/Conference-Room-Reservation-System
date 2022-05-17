package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.OrganizationNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.OrganizationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream().map(org -> new OrganizationMapper(passwordEncoder).mapToDto(org)).toList();
    }

    public OrganizationDto getOrganization(Long id) {
        return new OrganizationMapper(passwordEncoder).mapToDto(getOrganizationFromDatabase(id));
    }

    public OrganizationDto add(final OrganizationRequest request) {
        final Organization organization = new OrganizationMapper(passwordEncoder).mapToEntity(request);
        return new OrganizationMapper(passwordEncoder).mapToDto(organizationRepository.save(organization));
    }

    public OrganizationDto update(Long id, final OrganizationRequest request) {
        final Organization organizationFromDb = getOrganizationFromDatabase(id);
        final Organization organizationFromRequest = new OrganizationMapper(passwordEncoder).mapToEntity(request);
        organizationFromRequest.setOrganizationId(organizationFromDb.getOrganizationId());
        organizationFromRequest.setConferenceRooms(organizationFromDb.getConferenceRooms());

        return new OrganizationMapper(passwordEncoder).mapToDto(organizationRepository.save(organizationFromRequest));
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
