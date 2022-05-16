package com.sda.conferenceroomreservationsystem.model.request;

import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
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
            if (r.getReservationStart().isBefore(reservationEnd.minusSeconds(1)) && !r.getReservationEnd().isBefore(reservationStart.plusSeconds(1)) ||
                    r.getReservationEnd().isAfter(reservationStart.plusSeconds(1)) && !r.getReservationStart().isAfter(reservationEnd.minusSeconds(1))) {
                isOccupied = true;
            }
        }

        return isOccupied;
    }
}
