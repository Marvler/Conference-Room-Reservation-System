//package com.sda.conferenceroomreservationsystem.rest;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sda.conferenceroomreservationsystem.model.entity.Organization;
//import com.sda.conferenceroomreservationsystem.model.request.ConferenceRoomRequest;
//import com.sda.conferenceroomreservationsystem.model.request.OrganizationRequest;
//import com.sda.conferenceroomreservationsystem.service.ConferenceRoomService;
//import com.sda.conferenceroomreservationsystem.service.OrganizationService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.hamcrest.Matchers.containsString;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class ConferenceRoomControllerIntegrationTest {
//
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private ConferenceRoomService conferenceRoomService;
//
//    @Autowired
//    private OrganizationService organizationService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//
//    @Test
//    void addConferenceRoomShouldAddRecordToDatabase() throws Exception {
//        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wawel",  "2l", 2, 12,30, 1L);
//        OrganizationRequest organizationRequest = OrganizationRequest.of("Transporeon", "password", "transporeon@wp.pl");
//        long organizationId = 1L;
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(organizationRequest)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conference-room")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conference-room/" + organizationId + "/all"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$[0].conferenceRoomName").value("Wawel"));
//
//    }
//
//
//    @Test
//    void getAllConferenceRoomsShouldReturnCorrectRoomName() throws Exception {
//        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wawel",  "2l", 2, 12,30, 1l);
//        OrganizationRequest organizationRequest = OrganizationRequest.of("Transporeon", "password", "transporeon@wp.pl");
//        Long organizationId = 2L;
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(organizationRequest)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conference-room")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("Wawel")));
//
//    }
//
//    @Test
//    void updateConferenceRoomShouldUpdateRecordInDatabase() throws Exception {
//        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wawel",  "2l", 2, 12,30, 1L);
//        OrganizationRequest organizationRequest = OrganizationRequest.of("Transporeon", "password", "transporeon@wp.pl");
//        ConferenceRoomRequest requestToUpdate = ConferenceRoomRequest.of("WawelAfterUpdate",  "2l", 2, 12,30, 1L);
//        long organizationId = 1L;
//        long conferenceRoomId = 2L;
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(organizationRequest)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conference-room")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conference-room/" + organizationId + "/all"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$[0].conferenceRoomName").value("Wawel"));
//
//        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/api/conference-room/2")
//                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestToUpdate)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conference-room/" + organizationId + "/all"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$[0].conferenceRoomName").value("WawelAfterUpdate"));
//
//    }
//
//    @Test
//    void deleteConferenceRoomShouldDeleteRecordFromDatabase() throws Exception {
//        ConferenceRoomRequest request = ConferenceRoomRequest.of("Wawel",  "2l", 2, 12,30, 1L);
//        OrganizationRequest organizationRequest = OrganizationRequest.of("Transporeon", "password", "transporeon@wp.pl");
//        ConferenceRoomRequest requestToUpdate = ConferenceRoomRequest.of("WawelAfterUpdate",  "2l", 2, 12,30, 1l);
//        long organizationId = 1L;
//        long conferenceRoomId = 2L;
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(organizationRequest)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conference-room")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conference-room/" + organizationId + "/all"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$[0].conferenceRoomName").value("Wawel"));
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/api/conference-room/" + conferenceRoomId))
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conference-room/" + organizationId + "/all"))
//                .andDo(print())
//                .andExpect(content().string(containsString("[]")))
//                .andExpect(status().is2xxSuccessful());
//    }
//
//}