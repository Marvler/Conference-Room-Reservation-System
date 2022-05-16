package com.sda.conferenceroomreservationsystem.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
public class OrganizationRequest {

    @NotNull
    private String organizationName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
