package com.barriSenseBack.barrisense_feedback_api.service;


import com.barriSenseBack.barrisense_feedback_api.dto.FeedbackCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import com.barriSenseBack.barrisense_feedback_api.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Recupera todos los feedbacks de la base de datos.
     * @return una lista de feedbacks.
     */
    public List<Feedback> findAll() {
        // Llama directamente al método findAll() que nos da JpaRepository.
        return feedbackRepository.findAll();
    }

    /**
     * Busca un feedback por su ID.
     * @param id El ID del feedback a buscar.
     * @return un Optional que contiene el feedback si se encuentra, o un Optional vacío si no.
     */
    public Optional<Feedback> findById(Long id) {

        return feedbackRepository.findById(id);
    }


    /**
     * Calcula el número de quejas para un barrio y devuelve el resultado en un DTO.
     * @param hoodId El ID del barrio.
     * @return un DTO con el ID del barrio y el total de quejas.
     */
    public FeedbackCountDTO countComplaintsByHoodId(Long hoodId) {
        long count = feedbackRepository.countByHoodId(hoodId);
        return new FeedbackCountDTO(hoodId, count);
    }


    /**
     * Devuelve todas las quejas asociadas a un ID de barrio.
     * @param hoodId El ID del barrio.
     * @return una lista de objetos Feedback.
     */
    public List<Feedback> findAllByHoodId(Long hoodId) {
        return feedbackRepository.findByHoodId(hoodId);
    }


    /**
     * Devuelve una lista con el total de quejas para cada barrio.
     * @return una lista de objetos FeedbackCountDTO.
     */
    public List<FeedbackCountDTO> countAllComplaintsByNeighborhood() {
        return feedbackRepository.countAllGroupByHoodId();
    }
}