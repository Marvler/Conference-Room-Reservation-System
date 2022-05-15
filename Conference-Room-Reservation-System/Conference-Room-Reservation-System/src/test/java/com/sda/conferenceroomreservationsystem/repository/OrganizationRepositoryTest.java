package com.sda.conferenceroomreservationsystem.repository;

import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    void findByOrganizationNameShouldReturnOrganizationEntity() {
        String organizationName = "Transporeon";

        Organization organization = new Organization();
        organization.setOrganizationName(organizationName);

        organizationRepository.save(organization);

        Optional<Organization> organizationFoundByName = organizationRepository.findByOrganizationName(organizationName);
        Assertions.assertThat(organizationFoundByName.get().getOrganizationName()).isEqualTo(organizationName);

    }


    @Test
    void findByOrganizationNameAndPasswordShouldReturnOrganizationEntity() {
        String organizationName = "Transporeon";
        String passwordForOrganization = "password";

        Organization organization = new Organization();
        organization.setOrganizationName(organizationName);
        organization.setPassword(passwordForOrganization);

        organizationRepository.save(organization);

        Optional<Organization> organizationFoundByNameAndPassword = organizationRepository.findByOrganizationNameAndPassword(organizationName, passwordForOrganization);
        Assertions.assertThat(organizationFoundByNameAndPassword.get().getOrganizationName()).isEqualTo(organizationName);
        Assertions.assertThat(organizationFoundByNameAndPassword.get().getPassword()).isEqualTo(passwordForOrganization);
    }
}