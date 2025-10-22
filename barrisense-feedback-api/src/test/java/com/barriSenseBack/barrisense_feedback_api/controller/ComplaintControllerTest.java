package com.barriSenseBack.barrisense_feedback_api.controller;

import com.barriSenseBack.barrisense_feedback_api.dto.ComplaintCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Complaint;
import com.barriSenseBack.barrisense_feedback_api.service.ComplaintService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComplaintController.class)
class ComplaintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComplaintService complaintService;


    @TestConfiguration
    static class TestConfig {
        @Bean
        public ComplaintService complaintService() {
            return Mockito.mock(ComplaintService.class);
        }
    }

    @Test
    void whenGetAllComplaints_shouldReturnComplaintList() throws Exception {
        // ARRANGE
        Complaint complaint1 = Complaint.builder()
                .id(1L).userId(1L).hoodId(1L).hoodName("Hood1").content("Content1").build();
        List<Complaint> complaintList = Arrays.asList(complaint1);
        when(complaintService.findAll()).thenReturn(complaintList);

        // ACT & ASSERT
        mockMvc.perform(get("/api/complaints"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].content").value("Content1"));
    }

    @Test
    void whenGetComplaintById_withValidId_shouldReturnComplaint() throws Exception {
        // ARRANGE
        Complaint complaint = Complaint.builder()
                .id(1L).userId(1L).hoodId(1L).hoodName("Hood1").content("Content1").build();
        when(complaintService.findById(1L)).thenReturn(Optional.of(complaint));

        // ACT & ASSERT
        mockMvc.perform(get("/api/complaints/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hoodName").value("Hood1"));
    }

    @Test
    void whenGetComplaintById_withInvalidId_shouldReturnNotFound() throws Exception {
        // ARRANGE
        when(complaintService.findById(99L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        mockMvc.perform(get("/api/complaints/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetComplaintCountByNeighborhood_shouldReturnCountDTO() throws Exception {
        // ARRANGE
        Long hoodId = 2L;
        ComplaintCountDTO dto = new ComplaintCountDTO(hoodId, 42L);
        when(complaintService.countComplaintsByHoodId(hoodId)).thenReturn(dto);

        // ACT & ASSERT
        mockMvc.perform(get("/api/complaints/count/by-neighborhood/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.neighborhoodId").value(2))
                .andExpect(jsonPath("$.complaintCount").value(42));
    }

    @Test
    void whenGetComplaintsByNeighborhood_shouldReturnFilteredList() throws Exception {
        // ARRANGE
        Long hoodId = 3L;
        Complaint complaint1 = Complaint.builder()
                .userId(1L).hoodId(hoodId).hoodName("Hood3").content("ContentA").build();
        List<Complaint> filteredList = Arrays.asList(complaint1);
        when(complaintService.findAllByHoodId(hoodId)).thenReturn(filteredList);

        // ACT & ASSERT
        mockMvc.perform(get("/api/complaints/by-neighborhood/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].hoodId").value(3));
    }

    @Test
    void whenGetAllComplaintCountsByNeighborhood_shouldReturnDtoList() throws Exception {
        // ARRANGE
        ComplaintCountDTO dto1 = new ComplaintCountDTO(1L, 15L);
        ComplaintCountDTO dto2 = new ComplaintCountDTO(2L, 42L);
        List<ComplaintCountDTO> mockDtoList = Arrays.asList(dto1, dto2);
        when(complaintService.countAllComplaintsByNeighborhood()).thenReturn(mockDtoList);

        // ACT & ASSERT
        mockMvc.perform(get("/api/complaints/count/by-neighborhood/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].neighborhoodId").value(1))
                .andExpect(jsonPath("$[0].complaintCount").value(15))
                .andExpect(jsonPath("$[1].neighborhoodId").value(2))
                .andExpect(jsonPath("$[1].complaintCount").value(42));
    }
}