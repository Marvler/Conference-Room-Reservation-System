package com.sda.conferenceroomreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long reservationId;
    @Column(unique = true)
    private String reservationIdentifier;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
    @ManyToOne
    @JoinColumn(name = "conferenceRoomId")
    private ConferenceRoom conferenceRoom;
}
