package com.sda.testreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue
    private Long organizationId;
    private String organizationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Organization")
    private List<ConferenceRoom> conferenceRooms;
}
