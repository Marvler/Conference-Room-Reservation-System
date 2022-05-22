package com.sda.conferenceroomreservationsystem.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.model.request.ReservationRequest;
import com.sda.conferenceroomreservationsystem.service.ConferenceRoomService;
import com.sda.conferenceroomreservationsystem.service.OrganizationService;
import com.sda.conferenceroomreservationsystem.service.ReservationService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    protected static final String  SERVER_URL= "http://localhost:";



    @Order(1)
    @Test
    void deleteReservationShouldDeleteReservation() throws Exception {
        long reservationToDeleteId = 4L;
        OrganizationRequest organizationToAdd = OrganizationRequest.of("Krokus", "password", "krokus@wp.pl");
        ConferenceRoomRequest conferenceRoomToAdd = ConferenceRoomRequest.of("Fiołkowa", "2.12", 2, 22, 33, 2L);
        ReservationRequest reservationToAdd = ReservationRequest.of(LocalDateTime.of(2022, 5, 23, 11, 30), LocalDateTime.of(2022, 5, 23, 12, 0), 3L);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationToAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/conference-room")
                        .principal(() -> "Krokus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomToAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/reservation")
                        .principal(() -> "Krokus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationToAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.delete(SERVER_URL + port + "/api/reservation/" + reservationToDeleteId)
                        .principal(()-> "Krokus"))
                .andExpect(status().is2xxSuccessful());

    }
}

