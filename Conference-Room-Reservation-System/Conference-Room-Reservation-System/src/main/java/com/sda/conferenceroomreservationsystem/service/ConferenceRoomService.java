package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationService organizationService;



    public List<ConferenceRoomDto> getAll(String organizationName) {
        Organization organization = organizationService.getOrganizationFromDatabase(organizationName);
        return conferenceRoomRepository.findByOrganization(organization).stream()
                .map(ConferenceRoomMapper::mapToDto).toList();
    }

    public ConferenceRoomDto getConferenceRoom(Long id) {
        return ConferenceRoomMapper.mapToDto(getConferenceRoomFromDatabaseById(id));
    }

    public ConferenceRoomDto add(String organizationName, ConferenceRoomRequest request) {
        Organization organization = organizationService.getOrganizationFromDatabase(organizationName);
        final ConferenceRoom conferenceRoom = ConferenceRoomMapper.mapToEntity(organization, request);
        return ConferenceRoomMapper.mapToDto(conferenceRoomRepository.save(conferenceRoom));
    }

    public ConferenceRoomDto update(Long id, ConferenceRoomRequest request) {
        final ConferenceRoom conferenceRoomFromDb = getConferenceRoomFromDatabaseById(id);
        final ConferenceRoom conferenceRoomFromRequest = ConferenceRoomMapper.mapToEntity(request);
        conferenceRoomFromRequest.setConferenceRoomId(conferenceRoomFromDb.getConferenceRoomId());
        conferenceRoomFromRequest.setOrganization(conferenceRoomFromDb.getOrganization());
        conferenceRoomFromRequest.setReservations(conferenceRoomFromDb.getReservations());

        return ConferenceRoomMapper.mapToDto(conferenceRoomRepository.save(conferenceRoomFromRequest));
    }

    public void deleteConferenceRoomById(Long id) {
        conferenceRoomRepository.delete(getConferenceRoomFromDatabaseById(id));
    }

    public ConferenceRoom getConferenceRoomFromDatabaseById(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFormDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFormDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }
}