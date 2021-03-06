package com.sda.conferenceroomreservationsystem.model.request;

import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ReservationRequest {
    @NotNull
    private LocalDateTime reservationStart;
    @NotNull
    private LocalDateTime reservationEnd;
    @NotNull
    private Long conferenceRoomId;

    public boolean isOccupied(List<Reservation> reservations) {
        boolean isOccupied = false;

        for (Reservation r : reservations) {
            if (doesReservationCollide(r)) {
                isOccupied = true;
            }
        }
        return isOccupied;
    }

    public boolean isOccupiedUpdating(List<Reservation> reservations, Reservation updatedReservation) {
        boolean isOccupied = false;
        reservations.remove(updatedReservation);

        for (Reservation r : reservations) {
            if (doesReservationCollide(r)) {
                System.out.println("zajeta!");
                isOccupied = true;
            }
        }
        return isOccupied;
    }

    private boolean doesReservationCollide (Reservation reservation) {

        return reservation.getReservationStart().isBefore(reservationEnd.minusSeconds(1)) &&
                !reservation.getReservationEnd().isBefore(reservationStart.plusSeconds(1)) ||
                reservation.getReservationEnd().isAfter(reservationStart.plusSeconds(1)) &&
                !reservation.getReservationStart().isAfter(reservationEnd.minusSeconds(1));
    }
}
