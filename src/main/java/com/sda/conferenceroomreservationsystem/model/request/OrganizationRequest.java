package com.sda.conferenceroomreservationsystem.model.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrganizationRequest {

    @NotNull
    private String organizationName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
