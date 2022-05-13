package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomMapper conferenceRoomMapper;
}
