package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/conference-room")
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @GetMapping("/{organizationId}/all")
    public ResponseEntity<List<ConferenceRoomDto>> getAllConferenceRooms(@PathVariable("organizationId") final Long organizationId,
                                                                         Principal principal) {
        return ResponseEntity.ok(conferenceRoomService.getAll(organizationId, principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceRoomDto> getConferenceRoomById(@PathVariable("id") final Long id,
                                                                   Principal principal) {
        return ResponseEntity.ok(conferenceRoomService.getConferenceRoom(id, principal.getName()));
    }

    @PostMapping
    public ResponseEntity<ConferenceRoomDto> addConferenceRoom(@Valid @RequestBody final ConferenceRoomRequest request,
                                                               Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.add(request, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceRoomDto> updateConferenceRoom(@Valid @PathVariable("id") final Long id,
                                                                  @RequestBody final ConferenceRoomRequest request,
                                                                  Principal principal) {
        return ResponseEntity.ok(conferenceRoomService.update(id, request, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConferenceRoom(@PathVariable("id") final Long id,
                                                     Principal principal) {
        conferenceRoomService.deleteConferenceRoomById(id, principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}