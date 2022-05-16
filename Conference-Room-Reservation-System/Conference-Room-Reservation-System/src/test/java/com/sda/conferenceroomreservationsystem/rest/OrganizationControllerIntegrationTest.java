package com.sda.conferenceroomreservationsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
@AutoConfigureMockMvc
class OrganizationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void updateOrganizationShouldUpdateRecordInDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Transporeon", "sda", "transporeon@gmail.com");
        String organization = "Transporeon";
        OrganizationRequest requestUpdate = OrganizationRequest.of("Transporeon", "sda", "sda@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].email").value("transporeon@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/api/organization/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUpdate)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].email").value("sda@gmail.com"));

    }

    @Test
    void getOrganizationByNameShouldReturnOrganizationNotFoundException () throws Exception {
        OrganizationRequest requestToAdd = OrganizationRequest.of("Transporeon", "sda", "transporeon@gmail.com");
        int organizationToFind = 99;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestToAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/" + organizationToFind))
                .andDo(print())
                .andExpect(content().string(containsString("Organization not found!")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getAllOrganizationShouldReturnEmptyDatabase () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteOrganizationShouldDeleteRecordsFromDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Transporeon", "sda", "transporeon@gmail.com");
        int idToDelete = 1;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].organizationName").value("Transporeon"));


        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/api/organization/" + idToDelete))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void addOrganizationShouldAddRecordToDatabase() throws Exception {
        OrganizationRequest request = OrganizationRequest.of("Transporeon", "sda", "transporeon@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].organizationName").value("Transporeon"));
    }
}
