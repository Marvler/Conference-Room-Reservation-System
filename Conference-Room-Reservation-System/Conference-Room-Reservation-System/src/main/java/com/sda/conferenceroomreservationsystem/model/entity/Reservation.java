package com.sda.conferenceroomreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long reservationId;

    @NotBlank(message = "Reservation can't be null")
    @Size(min = 2, max = 20, message="Reservation identifier must have between 2-20 chars long")
    private String reservationIdentifier;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
    @ManyToOne
    @JoinColumn(name = "conferenceRoomId")
    private ConferenceRoom conferenceRoom;
}
