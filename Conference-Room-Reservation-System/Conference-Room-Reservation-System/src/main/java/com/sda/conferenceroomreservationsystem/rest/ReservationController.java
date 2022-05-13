package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.exception.type.ReservationAlreadyExistException;
import com.sda.conferenceroomreservationsystem.exception.type.ReservationNotFoundException;
import com.sda.conferenceroomreservationsystem.model.dto.ReservationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
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

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("name") final String name)
            throws ReservationNotFoundException
    {
        return ResponseEntity.ok(reservationService.getReservationByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody final Reservation reservation)
            throws ReservationAlreadyExistException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservation));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") final Long id,
                                                            @RequestBody final Reservation reservation)
            throws ReservationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(id, reservation));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id)
            throws ReservationNotFoundException {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}