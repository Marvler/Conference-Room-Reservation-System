package com.sda.conferenceroomreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
public class ConferenceRoom {
    @Id
    @GeneratedValue
    private Long conferenceRoomId;

    @NotBlank(message = "Conference room name can't be null")
    @Size(min = 2, max = 20, message="Conference room name must be between 2-20 chars long")
    private String conferenceRoomName;
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
