package com.sda.conferenceroomreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue
    private Long organizationId;

    @NotBlank(message = "Organization name can't be null")
    @Size(min = 2, max = 20, message= "Organization name must be 2 - 20 chars long")
    private String organizationName;
    private String password;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private List<ConferenceRoom> conferenceRooms;
}
