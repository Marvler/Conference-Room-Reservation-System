package com.sda.conferenceroomreservationsystem.configuration;

import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void init(ContextRefreshedEvent event) {
        if (organizationRepository.findByOrganizationName("admin").isEmpty()) {
            organizationRepository.save(new Organization(null, "admin", passwordEncoder.encode("admin"), "mail@mail.com", new ArrayList<>()));
        }
    }
}
