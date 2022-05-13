package com.sda.conferenceroomreservationsystem.rest;


import com.sda.conferenceroomreservationsystem.exception.type.ConferenceRoomAlreadyExistsException;
import com.sda.conferenceroomreservationsystem.exception.type.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
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
        List<ConferenceRoomDto> conferenceRooms = conferenceRoomService.getAllConferenceRooms;
        return new ResponseEntity<>(conferenceRooms, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<ConferenceRoomDto> getConferenceRoomById(@PathVariable("name") final String name)
            throws ConferenceRoomNotFoundException
    {
        return ResponseEntity.ok(conferenceRoomService.getConferenceRoomByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<ConferenceRoomDto> addConferenceRoom(@RequestBody final ConferenceRoomDto conferenceRoomDto)
            throws ConferenceRoomAlreadyExistsException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.createConferenceRoom(conferenceRoomDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConferenceRoomDto> updateConferenceRoom(@PathVariable("id") final Long id,
                                                                  @RequestBody final ConferenceRoom conferenceRoom)
            throws ConferenceRoomNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.updateConferenceRoom(id, conferenceRoom));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConferenceRoom(@PathVariable("id") Long id)
            throws ConferenceRoomNotFoundException {
        conferenceRoomService.deleteConferenceRoomById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}