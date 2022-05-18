package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomAlreadyExistsException;
import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomIdentifierAlreadyExistsException;
import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationService organizationService;



    public List<ConferenceRoomDto> getAll(Long organizationId, String principal) {
        Organization organization = organizationService.getOrganizationFromDatabase(organizationId);

        OrganizationService.principalValidator(organization, principal);

        return conferenceRoomRepository.findByOrganization(organization).stream()
                .map(ConferenceRoomMapper::mapToDto).toList();
    }

    public ConferenceRoomDto getConferenceRoom(Long id, String principal) {
        ConferenceRoom conferenceRoom = getConferenceRoomFromDatabaseById(id);

        principalValidator(conferenceRoom.getOrganization(), principal);

        return ConferenceRoomMapper.mapToDto(conferenceRoom);
    }

    public ConferenceRoomDto add(ConferenceRoomRequest request, String principal) {
        Organization organization = organizationService.getOrganizationFromDatabase(request.getOrganizationId());

        OrganizationService.principalValidator(organization, principal);
        final ConferenceRoom conferenceRoom = ConferenceRoomMapper.mapToEntity(organization, request);
        try {
            conferenceRoomRepository.save(conferenceRoom);
        } catch (DataIntegrityViolationException e) {
            if (conferenceRoomRepository.existsByConferenceRoomName(conferenceRoom.getConferenceRoomName())) {
                throw new ConferenceRoomAlreadyExistsException();
            }
            throw new ConferenceRoomIdentifierAlreadyExistsException();
        }
        return ConferenceRoomMapper.mapToDto(conferenceRoom);
    }

    public ConferenceRoomDto update(Long id, ConferenceRoomRequest request, String principal) {
        final ConferenceRoom conferenceRoomFromDb = getConferenceRoomFromDatabaseById(id);
        final ConferenceRoom conferenceRoomFromRequest = ConferenceRoomMapper.mapToEntity(request);

        principalValidator(conferenceRoomFromDb.getOrganization(), principal);

        conferenceRoomFromRequest.setConferenceRoomId(conferenceRoomFromDb.getConferenceRoomId());
        conferenceRoomFromRequest.setOrganization(conferenceRoomFromDb.getOrganization());
        conferenceRoomFromRequest.setReservations(conferenceRoomFromDb.getReservations());

        return ConferenceRoomMapper.mapToDto(conferenceRoomRepository.save(conferenceRoomFromRequest));
    }

    public void deleteConferenceRoomById(Long id, String principal) {
        ConferenceRoom conferenceRoom = getConferenceRoomFromDatabaseById(id);

        principalValidator(conferenceRoom.getOrganization(), principal);

        conferenceRoomRepository.delete(conferenceRoom);
    }

    public ConferenceRoom getConferenceRoomFromDatabaseById(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFormDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFormDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }

    public static void principalValidator(Organization organization, String name) {
        if (!organization.getOrganizationName().equals(name)) {
            throw new ConferenceRoomNotFoundException();
        }
    }
}