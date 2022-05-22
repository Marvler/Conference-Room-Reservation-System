package com.sda.conferenceroomreservationsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.service.OrganizationService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class ConferenceRoomControllerIntegrationTest {


    @LocalServerPort
    private int port;

    @Autowired
    private OrganizationService conferenceRoomService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    protected static final String SERVER_URL = "http://localhost:";


    @Order(2)
    @Test
    void getAllConferenceRoomsShouldReturnCorrectRoomName() throws Exception {
        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wawel",  "2l", 2, 12,30,4L);
        OrganizationRequest organizationRequest = OrganizationRequest.of("Transporeon", "password", "transporeon@wp.pl");
        Long organizationId = 4L;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/conference-room/")
                        .principal(() -> "Transporeon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(3)
    @Test
    void deleteConferenceRoomShouldDeleteRecordFromDatabase() throws Exception {
        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wawel",  "3l", 2, 12,30, 2L);
        OrganizationRequest organizationRequest = OrganizationRequest.of("Dulux", "password", "dulux@wp.pl");
        long organizationId = 2L;
        long conferenceRoomId = 3L;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conference-room")
                        .principal(() -> "Dulux")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/conference-room/" + organizationId + "/all")
                .principal(() -> "Dulux"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].conferenceRoomName").value("Wawel"));

        mockMvc.perform(MockMvcRequestBuilders.delete(SERVER_URL + port + "/api/conference-room/" + conferenceRoomId)
                        .principal(() -> "Dulux"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/conference-room/" + organizationId + "/all")
                        .principal(() -> "Dulux"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }


    @Order(1)
    @Test
    void updateConferenceRoomShouldUpdateRecordInDatabase() throws Exception {
        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wisla",  "7.12", 7, 12,30, 6L);
        OrganizationRequest organizationRequest = OrganizationRequest.of("Ikea", "password", "ikea@wp.pl");
        ConferenceRoomRequest requestToUpdate = ConferenceRoomRequest.of("Cracovia",  "7.12", 7, 12,30, 6L);
        long organizationId = 6L;
        long conferenceRoomId = 7L;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/conference-room")
                        .principal(() -> "Ikea")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/conference-room/" + organizationId + "/all")
                .principal(() -> "Ikea"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].conferenceRoomName").value("Wisla"));

        mockMvc.perform(MockMvcRequestBuilders.put(SERVER_URL + port + "/api/conference-room/" + conferenceRoomId)
                        .principal(() -> "Ikea")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestToUpdate)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/conference-room/" + organizationId + "/all")
                        .principal(() -> "Ikea"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].conferenceRoomName").value("Cracovia"));

    }


}