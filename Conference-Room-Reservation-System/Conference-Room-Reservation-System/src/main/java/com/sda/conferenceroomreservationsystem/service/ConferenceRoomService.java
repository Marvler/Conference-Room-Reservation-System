package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.exception.type.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    public List<ConferenceRoomDto> getAllConferenceRooms;


    public List<ConferenceRoomDto> getAllConferenceRooms() {
        //TODO
        return null;
    }

    public ConferenceRoomDto getConferenceRoomByName(String name) {
        //TODO
        return null;
    }
    public ConferenceRoomDto createConferenceRoom(ConferenceRoomDto conferenceRoomDto) {
        //TODO
        return null;
    }

    public ConferenceRoomDto updateConferenceRoom(Long id, ConferenceRoom conferenceRoom) {
        //TODO
        return null;
    }

    public void deleteConferenceRoomById(Long id)
            throws ConferenceRoomNotFoundException {
        //TODO
        conferenceRoomRepository.delete(getConferenceRoomFromDatabaseById(id));
    }

    private ConferenceRoom getConferenceRoomFromDatabaseById(final Long conferenceRoomId)
            throws ConferenceRoomNotFoundException {
        final Optional<ConferenceRoom> conferenceRoomFormDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFormDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }
}
