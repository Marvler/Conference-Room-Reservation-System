package com.sda.conferenceroomreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder(setterPrefix = "with", builderMethodName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue
    private Long organizationId;
    @Column(unique = true)
    private String organizationName;
    private String password;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private List<ConferenceRoom> conferenceRooms;
}
