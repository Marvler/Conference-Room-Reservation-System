package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.exception.OrganizationAlreadyExistsException;
import com.sda.conferenceroomreservationsystem.exception.OrganizationNotFoundException;
import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;


    @GetMapping("/all")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAll());
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<OrganizationDto> getOrganizationByName(@PathVariable("name") final String name) {
        OrganizationDto organization = organizationService.getOrganization(name);
        return ResponseEntity.ok(organization);
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody final OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.add(organizationRequest));
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("name") final String name,
                                                              @RequestBody final OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.update(name, organizationRequest));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("name") String name) {
        organizationService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}