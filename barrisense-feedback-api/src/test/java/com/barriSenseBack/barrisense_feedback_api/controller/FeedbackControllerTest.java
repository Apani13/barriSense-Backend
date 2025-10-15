package com.barriSenseBack.barrisense_feedback_api.controller;

import com.barriSenseBack.barrisense_feedback_api.dto.FeedbackCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import com.barriSenseBack.barrisense_feedback_api.service.FeedbackService;
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

@WebMvcTest(FeedbackController.class)
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private FeedbackService feedbackService;


    @TestConfiguration
    static class TestConfig {

        @Bean
        public FeedbackService feedbackService() {
            return Mockito.mock(FeedbackService.class);
        }
    }



    @Test
    void whenGetAllFeedbacks_shouldReturnFeedbackList() throws Exception {
        // ARRANGE
        Feedback feedback1 = new Feedback(1L, 1L, "Hood1", "Content1");
        feedback1.setId(1L);
        List<Feedback> feedbackList = Arrays.asList(feedback1);
        when(feedbackService.findAll()).thenReturn(feedbackList);

        // ACT ASSERT
        mockMvc.perform(get("/api/feedbacks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].content").value("Content1"));
    }

    @Test
    void whenGetFeedbackById_withValidId_shouldReturnFeedback() throws Exception {
        // ARRANGE
        Feedback feedback = new Feedback(1L, 1L, "Hood1", "Content1");
        feedback.setId(1L);
        when(feedbackService.findById(1L)).thenReturn(Optional.of(feedback));

        // ACT ASSERT
        mockMvc.perform(get("/api/feedbacks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hoodName").value("Hood1"));
    }

    @Test
    void whenGetFeedbackById_withInvalidId_shouldReturnNotFound() throws Exception {
        // ARRANGE
        when(feedbackService.findById(99L)).thenReturn(Optional.empty());

        // ACT ASSERT
        mockMvc.perform(get("/api/feedbacks/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetComplaintCountByNeighborhood_shouldReturnCountDTO() throws Exception {
        // ARRANGE
        Long hoodId = 2L;
        FeedbackCountDTO dto = new FeedbackCountDTO(hoodId, 42L);
        when(feedbackService.countComplaintsByHoodId(hoodId)).thenReturn(dto);

        // ACT ASSERT
        mockMvc.perform(get("/api/feedbacks/count/by-neighborhood/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.neighborhoodId").value(2))
                .andExpect(jsonPath("$.FeedbackCount").value(42));
    }

    @Test
    void whenGetFeedbacksByNeighborhood_shouldReturnFilteredList() throws Exception {
        // ARRANGE
        Long hoodId = 3L;
        Feedback feedback1 = new Feedback(1L, hoodId, "Hood3", "ContentA");
        List<Feedback> filteredList = Arrays.asList(feedback1);
        when(feedbackService.findAllByHoodId(hoodId)).thenReturn(filteredList);

        // ACT ASSERT
        mockMvc.perform(get("/api/feedbacks/by-neighborhood/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].hoodId").value(3));
    }
}