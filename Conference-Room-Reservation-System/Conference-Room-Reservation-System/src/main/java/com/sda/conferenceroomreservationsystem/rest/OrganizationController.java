package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.exception.type.OrganizationNotFoundException;
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
        List<OrganizationDto> organizations = organizationService.getAll();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<OrganizationDto> getOrganizationByName(@PathVariable("name") final String name)
            throws OrganizationNotFoundException {
        OrganizationDto organization = organizationService.getOrganization(name);
        return new ResponseEntity<>(organization, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Organization> addOrganization(@RequestBody final OrganizationRequest organizationRequest)
        //    throws OrganizationAlreadyExistsException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.add(organizationRequest));
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("name") final String name,
                                                              @RequestBody final OrganizationRequest organizationRequest)
            throws OrganizationNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.update(name, organizationRequest));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("name") String name)
            throws OrganizationNotFoundException {
        organizationService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}