package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/conference-room")
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @GetMapping("/{organizationId}/all")
    public ResponseEntity<List<ConferenceRoomDto>> getAllConferenceRooms(@PathVariable("organizationId") final Long organizationId) {
        return ResponseEntity.ok(conferenceRoomService.getAll(organizationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceRoomDto> getConferenceRoomById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(conferenceRoomService.getConferenceRoom(id));
    }

    @PostMapping
    public ResponseEntity<ConferenceRoomDto> addConferenceRoom(@Valid @RequestBody final ConferenceRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceRoomDto> updateConferenceRoom(@Valid @PathVariable("id") final Long id,
                                                                  @RequestBody final ConferenceRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConferenceRoom(@PathVariable("id") final Long id) {
        conferenceRoomService.deleteConferenceRoomById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}