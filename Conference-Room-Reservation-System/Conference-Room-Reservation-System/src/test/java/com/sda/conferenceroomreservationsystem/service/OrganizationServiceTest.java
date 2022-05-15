package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.mapper.OrganizationMapper;
import com.sda.conferenceroomreservationsystem.model.dto.OrganizationDto;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationMapper organizationMapper;

    @InjectMocks
    private OrganizationService organizationService;

    @Test
    void getAllOrganizationRecordsReturnsListOfOrganizationRecordsWhenDatabaseIsNotEmpty() {
        final Organization organization = new Organization();
        organization.setOrganizationName("Transporeon");

        Mockito.when(organizationRepository.findAll())
                .thenReturn(List.of(organization, new Organization()));

        final List<OrganizationDto> resultOrganizationRecords = organizationService.getAll();

        Assertions.assertThat(resultOrganizationRecords).hasSize(2);
        Assertions.assertThat(resultOrganizationRecords.get(0).getOrganizationName()).isEqualTo("Transporeon");
    }
}