package com.sda.conferenceroomreservationsystem.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
