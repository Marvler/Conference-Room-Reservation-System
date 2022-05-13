package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;
}
