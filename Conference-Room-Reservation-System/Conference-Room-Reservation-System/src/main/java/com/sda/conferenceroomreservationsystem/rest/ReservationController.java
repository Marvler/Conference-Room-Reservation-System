package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.request.ReservationRequest;
import com.sda.conferenceroomreservationsystem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/{conferenceRoom}/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations(@PathVariable("conferenceRoom") final Long conferenceRoom) {
        return ResponseEntity.ok(reservationService.getAll(conferenceRoom));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") final Long id) {
        ReservationDto reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/{conferenceRoom}/add")
    public ResponseEntity<ReservationDto> addReservation(@PathVariable("conferenceRoom") final Long conferenceRoom,
                                                      @RequestBody final ReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.add(conferenceRoom, request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") final Long id,
                                                            @RequestBody final ReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") final Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}