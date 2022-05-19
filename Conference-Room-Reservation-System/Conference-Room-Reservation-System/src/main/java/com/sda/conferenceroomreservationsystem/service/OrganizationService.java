package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.OrganizationAlreadyExistsException;
import com.sda.conferenceroomreservationsystem.exception.OrganizationNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.OrganizationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final PasswordEncoder passwordEncoder;

    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream().map(organizationMapper::mapToDto).toList();
    }

    public OrganizationDto getOrganization(String principal, Long id) {
        Organization organization = getOrganizationFromDatabase(id);

        PrincipalValidator.validateOrganization(organization, principal);

        return organizationMapper.mapToDto(organization);
    }

    public OrganizationDto getOrganizationId(String name) {
        return organizationMapper.mapToDto(getOrganizationIdFromDatabaseWithAuth(name));
    }

    public OrganizationDto add(final OrganizationRequest request) {
        final Organization organization = organizationMapper.mapToEntity(request);
        try {
            organizationRepository.save(organization);
        } catch (DataIntegrityViolationException e) {
            throw new OrganizationAlreadyExistsException();
        }
        return organizationMapper.mapToDto(organization);
    }

    @Transactional
    public OrganizationDto update(Long id, final OrganizationRequest request, String principal) {
        final Organization organizationFromDb = getOrganizationFromDatabase(id);
        final Organization organizationFromRequest = organizationMapper.mapToEntity(request);

        PrincipalValidator.validateOrganization(organizationFromDb, principal);

        organizationFromRequest.setOrganizationId(organizationFromDb.getOrganizationId());
        organizationFromRequest.setConferenceRooms(organizationFromDb.getConferenceRooms());

        return organizationMapper.mapToDto(organizationRepository.save(organizationFromRequest));
    }

    public void deleteOrganizationById(Long id, String principal) {
        Organization organization = getOrganizationFromDatabase(id);

        PrincipalValidator.validateOrganization(organization, principal);

        organizationRepository.delete(organization);
    }

    public Organization getOrganizationFromDatabase(Long id) {
        final Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElseThrow(OrganizationNotFoundException::new);
    }

    public Organization getOrganizationIdFromDatabaseWithAuth(String name) {
        final Optional<Organization> organization = organizationRepository.findByOrganizationName(name);
        return organization.orElse(null);
    }
}
