package com.sda.conferenceroomreservationsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor(staticName = "of")
public class OrganizationAuthRequest {

    @NotBlank
    @Size(min = 2, max = 20, message="must be between 2-20 chars long")
    private String organizationName;
}
