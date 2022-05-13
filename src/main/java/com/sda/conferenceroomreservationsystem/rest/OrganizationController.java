package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
}
