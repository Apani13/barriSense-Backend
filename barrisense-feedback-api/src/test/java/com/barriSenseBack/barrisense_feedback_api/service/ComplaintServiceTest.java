package com.barriSenseBack.barrisense_feedback_api.service;

import com.barriSenseBack.barrisense_feedback_api.dto.ComplaintCountDTO; // <-- DTO RENOMBRADO
import com.barriSenseBack.barrisense_feedback_api.entity.Complaint;
import com.barriSenseBack.barrisense_feedback_api.repository.ComplaintRepository;
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
class ComplaintServiceTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    void whenFindAll_shouldReturnComplaintList() {
        // ARRANGE
        Complaint complaint1 = Complaint.builder()
                .userId(1L).hoodId(1L).hoodName("Test Hood 1").content("Content 1").build();
        Complaint complaint2 = Complaint.builder()
                .userId(2L).hoodId(2L).hoodName("Test Hood 2").content("Content 2").build();
        List<Complaint> complaintList = Arrays.asList(complaint1, complaint2);

        when(complaintRepository.findAll()).thenReturn(complaintList);

        // ACT
        List<Complaint> result = complaintService.findAll();

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Content 1", result.get(0).getContent());
    }

    @Test
    void whenFindById_withValidId_shouldReturnComplaint() {
        // ARRANGE
        Long complaintId = 1L;
        Complaint complaint = Complaint.builder()
                .id(complaintId).userId(1L).hoodId(1L).hoodName("Test Hood").content("Content").build();

        when(complaintRepository.findById(complaintId)).thenReturn(Optional.of(complaint));

        // ACT
        Optional<Complaint> result = complaintService.findById(complaintId);

        // ASSERT
        assertTrue(result.isPresent());
        assertEquals(complaintId, result.get().getId());
    }

    @Test
    void whenFindById_withInvalidId_shouldReturnEmptyOptional() {
        // ARRANGE
        Long invalidId = 99L;
        when(complaintRepository.findById(invalidId)).thenReturn(Optional.empty());

        // ACT
        Optional<Complaint> result = complaintService.findById(invalidId);

        // ASSERT
        assertFalse(result.isPresent());
    }

    @Test
    void whenCountComplaintsByHoodId_shouldReturnCorrectCount() {
        // ARRANGE
        Long hoodId = 3L;
        long expectedCount = 15L;
        when(complaintRepository.countByHoodId(hoodId)).thenReturn(expectedCount);

        // ACT
        ComplaintCountDTO result = complaintService.countComplaintsByHoodId(hoodId);

        // ASSERT
        assertNotNull(result);
        assertEquals(hoodId, result.neighborhoodId());
        assertEquals(expectedCount, result.complaintCount());
    }

    @Test
    void whenFindAllByHoodId_shouldReturnFilteredList() {
        // ARRANGE
        Long hoodId = 5L;
        Complaint complaint1 = Complaint.builder()
                .userId(1L).hoodId(hoodId).hoodName("Hood 5").content("Content A").build();
        Complaint complaint2 = Complaint.builder()
                .userId(2L).hoodId(hoodId).hoodName("Hood 5").content("Content B").build();
        List<Complaint> filteredList = Arrays.asList(complaint1, complaint2);

        when(complaintRepository.findByHoodId(hoodId)).thenReturn(filteredList);

        // ACT
        List<Complaint> result = complaintService.findAllByHoodId(hoodId);

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(hoodId, result.get(0).getHoodId());
    }

    @Test
    void whenCountAllComplaintsByNeighborhood_shouldReturnDtoList() {
        // ARRANGE
        ComplaintCountDTO dto1 = new ComplaintCountDTO(1L, 15L);
        ComplaintCountDTO dto2 = new ComplaintCountDTO(2L, 42L);
        List<ComplaintCountDTO> mockDtoList = Arrays.asList(dto1, dto2);

        when(complaintRepository.countAllGroupByHoodId()).thenReturn(mockDtoList);

        // ACT
        List<ComplaintCountDTO> result = complaintService.countAllComplaintsByNeighborhood();

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).neighborhoodId());
        assertEquals(15L, result.get(0).complaintCount());
        assertEquals(42L, result.get(1).complaintCount());
    }
}