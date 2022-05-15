package com.sda.conferenceroomreservationsystem.rest;


import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomAlreadyExistsException;
import com.sda.conferenceroomreservationsystem.exception.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/conferenceroom")
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @GetMapping("/all")
    public ResponseEntity<List<ConferenceRoomDto>> getAllConferenceRooms() {
        return ResponseEntity.ok(conferenceRoomService.getAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ConferenceRoomDto> getConferenceRoomById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(conferenceRoomService.getConferenceRoom(id));
    }

    @PostMapping("/{organizationName}/add")
    public ResponseEntity<ConferenceRoom> addConferenceRoom(@PathVariable("organizationName") final String organizationName,
                                                            @RequestBody final ConferenceRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.add(organizationName, request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConferenceRoomDto> updateConferenceRoom(@PathVariable("id") final Long id,
                                                                  @RequestBody final ConferenceRoomRequest conferenceRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.update(id, conferenceRoomRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConferenceRoom(@PathVariable("id") Long id) {
        conferenceRoomService.deleteConferenceRoomById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}