package com.sda.conferenceroomreservationsystem.repository;

import ch.qos.logback.classic.spi.LoggingEventVO;
import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConferenceRoomRepositoryTest {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Order(1)
    @Test
    void findByOrganizationShouldReturnOrganizationFromConferenceRoomRepository() {
        ConferenceRoom conferenceRoomFirst = ConferenceRoom.Builder()
                .withConferenceRoomName("Wawel")
                .build();
        ConferenceRoom conferenceRoomSecond = ConferenceRoom.Builder()
                .withConferenceRoomName("Falafel")
                .build();

        List<ConferenceRoom> conferenceRoomsList = List.of(conferenceRoomFirst,conferenceRoomSecond);

        Organization tranporeon = Organization.Builder()
                .withOrganizationName("Transporeon")
                .withConferenceRooms(conferenceRoomsList)
                .build();

        Organization savedOrganization = organizationRepository.save(tranporeon);
        List<ConferenceRoom> lookedConferenceRoomList = conferenceRoomRepository.findByOrganization(tranporeon);

        assertThat(lookedConferenceRoomList).isNotNull();
        assertThat(lookedConferenceRoomList.contains("Transporeon"));
    }

    @Order(2)
    @Test
    void existsByConferenceRoomNameShouldReturnTrue() {
        String conferenceRoomName = "Wawel";

    final ConferenceRoom conferenceRoom = new ConferenceRoom();
    conferenceRoom.setConferenceRoomName(conferenceRoomName);

    ConferenceRoom conferenceRoomInDatabase = conferenceRoomRepository.save(conferenceRoom);
    boolean conferenceRoomExist = conferenceRoomRepository.existsByConferenceRoomName(conferenceRoomName);

    assertThat(conferenceRoomExist).isEqualTo(true);
    }




}