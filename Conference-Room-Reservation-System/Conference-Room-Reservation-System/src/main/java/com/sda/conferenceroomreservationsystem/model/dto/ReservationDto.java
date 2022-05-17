package com.sda.conferenceroomreservationsystem.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReservationDto {
    private Long reservationId;

    @NotBlank(message = "cant be null")
    @Size(min = 2, max = 20, message="must be between 2-20 chars long")
    private String reservationIdentifier;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
}
