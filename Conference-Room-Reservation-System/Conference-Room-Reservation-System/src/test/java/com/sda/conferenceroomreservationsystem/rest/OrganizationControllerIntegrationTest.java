package com.sda.conferenceroomreservationsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationsystem.model.entity.Organization;
import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
import com.sda.conferenceroomreservationsystem.service.OrganizationService;
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


    @Test
    void getAllOrganizationShouldReturnEmptyDatabase () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    void deleteOrganizationShouldDeleteRecordsFromDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Intel", "intel", "intel@gmail.com");
        int idToDelete = 3;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[2].organizationName").value("Intel"));

        mockMvc.perform(MockMvcRequestBuilders.delete(SERVER_URL + port + "/api/organization/" + idToDelete)
                        .principal(() -> "Intel"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }


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
                .andExpect(content().string(containsString("Sda")));
    }


    @Test
    void getOrganizationByNameShouldReturnOrganizationNotFoundException () throws Exception {
        String organization = "Ibm";
        Organization organization1 = Organization.Builder().withOrganizationName("Ibm").withOrganizationId(7L).build();

        OrganizationRequest requestToAdd = OrganizationRequest.of("Ibm", "ibm", "ibm@gmail.com");
        int organizationToFind = 99;

        mockMvc.perform(MockMvcRequestBuilders.post(SERVER_URL + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestToAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get(SERVER_URL + port + "/api/organization/" + organizationToFind)
                        .principal(() -> "Ibm"))
                .andDo(print())
                .andExpect(content().string(containsString("Organization not found")))
                .andExpect(status().is4xxClientError());
    }


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

        mockMvc.perform(MockMvcRequestBuilders.put(SERVER_URL + port + "/api/organization/" + 2)
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
