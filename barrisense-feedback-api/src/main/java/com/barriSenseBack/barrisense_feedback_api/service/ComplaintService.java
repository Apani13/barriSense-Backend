package com.barriSenseBack.barrisense_feedback_api.service;


import com.barriSenseBack.barrisense_feedback_api.dto.ComplaintCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Complaint;
import com.barriSenseBack.barrisense_feedback_api.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    /**
     * Recupera todos los feedbacks de la base de datos.
     * @return una lista de feedbacks.
     */
    public List<Complaint> findAll() {
        // Llama directamente al método findAll() que nos da JpaRepository.
        return complaintRepository.findAll();
    }

    /**
     * Busca un feedback por su ID.
     * @param id El ID del feedback a buscar.
     * @return un Optional que contiene el feedback si se encuentra, o un Optional vacío si no.
     */
    public Optional<Complaint> findById(Long id) {

        return complaintRepository.findById(id);
    }


    /**
     * Calcula el número de quejas para un barrio y devuelve el resultado en un DTO.
     * @param hoodId El ID del barrio.
     * @return un DTO con el ID del barrio y el total de quejas.
     */
    public ComplaintCountDTO countComplaintsByHoodId(Long hoodId) {
        long count = complaintRepository.countByHoodId(hoodId);
        return new ComplaintCountDTO(hoodId, count);
    }


    /**
     * Devuelve todas las quejas asociadas a un ID de barrio.
     * @param hoodId El ID del barrio.
     * @return una lista de objetos Feedback.
     */
    public List<Complaint> findAllByHoodId(Long hoodId) {
        return complaintRepository.findByHoodId(hoodId);
    }


    /**
     * Devuelve una lista con el total de quejas para cada barrio.
     * @return una lista de objetos FeedbackCountDTO.
     */
    public List<ComplaintCountDTO> countAllComplaintsByNeighborhood() {
        return complaintRepository.countAllGroupByHoodId();
    }


    public Complaint save(Complaint feedback) {
        return complaintRepository.save(feedback);
    }
}