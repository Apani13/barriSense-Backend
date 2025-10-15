package com.barriSenseBack.barrisense_feedback_api.service;

import com.barriSenseBack.barrisense_feedback_api.dto.FeedbackCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import com.barriSenseBack.barrisense_feedback_api.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    void whenFindAll_shouldReturnFeedbackList() {
        // ARRANGE
        Feedback feedback1 = new Feedback(); // Usa el constructor vacío
        feedback1.setUserId(1L);
        feedback1.setHoodId(1L);
        feedback1.setHoodName("Test Hood 1");
        feedback1.setContent("Content 1");


        Feedback feedback2 = new Feedback(); // Usa el constructor vacío
        feedback1.setUserId(2L);
        feedback1.setHoodId(2L);
        feedback1.setHoodName("Test Hood 1");
        feedback1.setContent("Content 1");
        List<Feedback> feedbackList = Arrays.asList(feedback1, feedback2);

        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        // ACT
        List<Feedback> result = feedbackService.findAll();

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Content 1", result.get(0).getContent());
    }

    @Test
    void whenFindById_withValidId_shouldReturnFeedback() {

        // ARRANGE
        Long feedbackId = 1L;


        Feedback feedback = new Feedback();
        feedback.setId(feedbackId);
        feedback.setUserId(1L);
        feedback.setHoodId(1L);
        feedback.setHoodName("Test Hood");
        feedback.setContent("Content");


        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(feedback));

        // ACT
        Optional<Feedback> result = feedbackService.findById(feedbackId);

        // ASSERT
        assertTrue(result.isPresent());
        assertEquals(feedbackId, result.get().getId()); // Esta línea ahora funcionará
    }

    @Test
    void whenFindById_withInvalidId_shouldReturnEmptyOptional() {
        // ARRANGE
        Long invalidId = 99L;
        when(feedbackRepository.findById(invalidId)).thenReturn(Optional.empty());

        // ACT
        Optional<Feedback> result = feedbackService.findById(invalidId);

        // ASSERT
        assertFalse(result.isPresent());
    }

    @Test
    void whenCountComplaintsByHoodId_shouldReturnCorrectCount() {
        // ARRANGE
        Long hoodId = 3L;
        long expectedCount = 15L;
        when(feedbackRepository.countByHoodId(hoodId)).thenReturn(expectedCount);

        // ACT
        FeedbackCountDTO result = feedbackService.countComplaintsByHoodId(hoodId);

        // ASSERT
        assertNotNull(result);
        assertEquals(hoodId, result.neighborhoodId());
        assertEquals(expectedCount, result.FeedbackCount());
    }

    @Test
    void whenFindAllByHoodId_shouldReturnFilteredList() {
        // ARRANGE
        Long hoodId = 5L;

        // --- ESTA ES LA PARTE "TRADUCIDA" ---

        // Creamos el primer feedback usando el constructor vacío y los setters
        Feedback feedback1 = new Feedback();
        feedback1.setUserId(1L);
        feedback1.setHoodId(hoodId);
        feedback1.setHoodName("Hood 5");
        feedback1.setContent("Content A");

        // Hacemos lo mismo para el segundo
        Feedback feedback2 = new Feedback();
        feedback2.setUserId(2L);
        feedback2.setHoodId(hoodId);
        feedback2.setHoodName("Hood 5");
        feedback2.setContent("Content B");

        // El resto del test se queda igual
        List<Feedback> filteredList = Arrays.asList(feedback1, feedback2);

        when(feedbackRepository.findByHoodId(hoodId)).thenReturn(filteredList);

        // ACT
        List<Feedback> result = feedbackService.findAllByHoodId(hoodId);

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(hoodId, result.get(0).getHoodId());
    }


    @Test
    void whenCountAllComplaintsByNeighborhood_shouldReturnDtoList() {
        // ARRANGE

        FeedbackCountDTO dto1 = new FeedbackCountDTO(1L, 15L);
        FeedbackCountDTO dto2 = new FeedbackCountDTO(2L, 42L);
        List<FeedbackCountDTO> mockDtoList = Arrays.asList(dto1, dto2);


        when(feedbackRepository.countAllGroupByHoodId()).thenReturn(mockDtoList);

        // ACT
        List<FeedbackCountDTO> result = feedbackService.countAllComplaintsByNeighborhood();

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).neighborhoodId());
        assertEquals(15L, result.get(0).FeedbackCount());
        assertEquals(42L, result.get(1).FeedbackCount());
    }
}
