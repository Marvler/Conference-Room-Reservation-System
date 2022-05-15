package com.sda.conferenceroomreservationsystem.rest;

import com.sda.conferenceroomreservationsystem.service.OrganizationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @MockBean
    private OrganizationService organizationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRecordsReturnNoOrganizationRecordsAndRespondsWithOkWhenOrganizationRecordsAreNotCreated() throws Exception{
        Mockito.when(organizationService.getAll())
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/all/"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is(200));
    }

}