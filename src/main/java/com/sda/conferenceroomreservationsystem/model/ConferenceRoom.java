package com.sda.conferenceroomreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConferenceRoom {
    @Id
    @GeneratedValue
    private Long conferenceRoomId;
    private String conferenceRoomName;
    private String conferenceRoomIdentifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;
    private Integer numberOfStandings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ConferenceRoom")
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "reservationId")
    private Organization organization;
}
