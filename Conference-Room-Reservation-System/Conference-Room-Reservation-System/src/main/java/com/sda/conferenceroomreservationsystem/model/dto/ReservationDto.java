package com.sda.conferenceroomreservationsystem.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private String reservationIdentifier;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
}
