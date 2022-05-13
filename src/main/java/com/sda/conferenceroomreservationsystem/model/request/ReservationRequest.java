package com.sda.conferenceroomreservationsystem.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    @NotNull
    private LocalDateTime reservationStart;
    @NotNull
    private LocalDateTime reservationEnd;
}
