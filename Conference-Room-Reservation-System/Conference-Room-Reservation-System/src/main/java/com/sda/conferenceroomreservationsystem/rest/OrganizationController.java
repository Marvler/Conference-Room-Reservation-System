package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationAuthRequest;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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

    @GetMapping("/auth/{name}")
    public ResponseEntity<Long> getOrganizationId(@PathVariable("name") final String name) {
        return ResponseEntity.ok(organizationService.getOrganizationId(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("id") final Long id,
                                                           Principal principal) {
        return ResponseEntity.ok(organizationService.getOrganization(principal.getName(), id));
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> addOrganization(@Valid @RequestBody final OrganizationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@Valid @PathVariable("id") final Long id,
                                                              @RequestBody final OrganizationRequest request,
                                                              Principal principal) {
        return ResponseEntity.ok(organizationService.update(id, request, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("id") final Long id,
                                                   Principal principal) {
        organizationService.deleteOrganizationById(id, principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}