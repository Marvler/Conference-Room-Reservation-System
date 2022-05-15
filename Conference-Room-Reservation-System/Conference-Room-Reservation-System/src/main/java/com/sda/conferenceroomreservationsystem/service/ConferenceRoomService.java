package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomMapper conferenceRoomMapper;
    private final OrganizationService organizationService;



    public List<ConferenceRoomDto> getAll() {
        return conferenceRoomRepository.findAll().stream().map(ConferenceRoomMapper::map).collect(Collectors.toList());
    }

    public ConferenceRoomDto getConferenceRoom(Long id) throws ConferenceRoomNotFoundException {
        return ConferenceRoomMapper.map(getConferenceRoomFromDatabaseById(id));
    }

    public ConferenceRoom add(String organizationName, ConferenceRoomRequest request) {
        Organization organization = organizationService.getOrganizationFromDatabase(organizationName);
        final ConferenceRoom conferenceRoom = conferenceRoomMapper.map(organization, request);
        return conferenceRoomRepository.save(conferenceRoom);
    }

    public ConferenceRoomDto update(Long id, ConferenceRoomRequest request) throws ConferenceRoomNotFoundException {
        final ConferenceRoom conferenceRoomFromDb = getConferenceRoomFromDatabaseById(id);
        final ConferenceRoom conferenceRoomFromRequest = conferenceRoomMapper.map(request);
        conferenceRoomFromRequest.setConferenceRoomId(conferenceRoomFromDb.getConferenceRoomId());
        conferenceRoomFromRequest.setOrganization(conferenceRoomFromDb.getOrganization());

        return conferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoomFromRequest));
    }

    public void deleteConferenceRoomById(Long id) throws ConferenceRoomNotFoundException {
        conferenceRoomRepository.delete(getConferenceRoomFromDatabaseById(id));
    }

    public ConferenceRoom getConferenceRoomFromDatabaseById(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFormDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFormDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }
}