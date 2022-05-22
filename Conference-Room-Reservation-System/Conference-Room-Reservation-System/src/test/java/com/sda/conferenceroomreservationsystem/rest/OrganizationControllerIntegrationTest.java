package com.sda.conferenceroomreservationsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.service.OrganizationService;
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



import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrganizationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    protected static final String SERVER_URL = "http://localhost:";

    @Order(1)
    @Test
    void getAllOrganizationShouldReturnEmptyDatabase () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Order(2)
    @Test
    void deleteOrganizationShouldDeleteRecordsFromDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Intel", "intel", "intel@gmail.com");
        int idToDelete = 2;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[1].organizationName").value("Intel"));

        mockMvc.perform(MockMvcRequestBuilders.delete(SERVER_URL + port + "/api/organization/" + idToDelete)
                        .principal(() -> "Intel"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Order(3)
    @Test
    void addOrganizationShouldAddRecordToDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Sda", "sda", "transporeon@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[1].organizationName").value("Sda"));
    }

    @Order(4)
    @Test
    void getOrganizationByNameShouldReturnOrganizationNotFoundException () throws Exception {
        String organization = "Transporeon";
        Organization organization1 = Organization.Builder().withOrganizationName("Transporeon").withOrganizationId(7L).build();

        OrganizationRequest requestToAdd = OrganizationRequest.of("Transporeon", "sda", "transporeon@gmail.com");
        int organizationToFind = 99;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestToAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/" + organizationToFind)
                        .principal(() -> "Transporeon"))
                .andDo(print())
                .andExpect(content().string(containsString("Organization not found")))
                .andExpect(status().is4xxClientError());
    }

    @Order(5)
    @Test
    void updateOrganizationShouldUpdateRecordInDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Comarch", "comarch", "comarch@gmail.com");
        OrganizationRequest requestUpdate = OrganizationRequest.of("Comarch2", "comarch", "sda@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put(SERVER_URL + port + "/api/organization/" + 5)
                        .principal(() -> "Comarch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUpdate)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Comarch2")));
    }
}