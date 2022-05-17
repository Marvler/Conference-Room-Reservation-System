package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.request.ReservationRequest;
import com.sda.conferenceroomreservationsystem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/{conferenceRoomId}/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations(@PathVariable("conferenceRoomId") final Long conferenceRoomId,
                                                                   Principal principal) {
        return ResponseEntity.ok(reservationService.getAll(conferenceRoomId, principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") final Long id,
                                                             Principal principal) {
        return ResponseEntity.ok(reservationService.getReservation(id, principal.getName()));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> addReservation(@RequestBody final ReservationRequest request,
                                                         Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.add(request, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") final Long id,
                                                            @RequestBody final ReservationRequest request,
                                                            Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.update(id, request, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") final Long id,
                                                  Principal principal) {
        reservationService.deleteReservationById(id, principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}