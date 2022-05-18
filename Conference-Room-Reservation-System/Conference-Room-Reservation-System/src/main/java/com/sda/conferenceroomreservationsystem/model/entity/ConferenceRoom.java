package com.sda.conferenceroomreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConferenceRoom {
    @Id
    @GeneratedValue
    private Long conferenceRoomId;
    @Column(unique = true)
    private String conferenceRoomName;
    @Column(unique = true)
    private String conferenceRoomIdentifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;
    private Integer numberOfStandings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conferenceRoom", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;

    public Boolean isAvailable() {
        Boolean availability = true;

        for (Reservation r : this.reservations) {
            if (r.getReservationStart().isBefore(LocalDateTime.now()) && r.getReservationEnd().isAfter(LocalDateTime.now())) {
                availability = false;
            }
        }

        return availability;
    }
}
