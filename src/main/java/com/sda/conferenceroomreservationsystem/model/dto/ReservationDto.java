package com.sda.conferenceroomreservationsystem.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationDto {
    private String reservationIdentifier;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
}
