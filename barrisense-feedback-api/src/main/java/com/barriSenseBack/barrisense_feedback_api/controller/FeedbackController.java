package com.barriSenseBack.barrisense_feedback_api.controller;


import com.barriSenseBack.barrisense_feedback_api.dto.FeedbackCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import com.barriSenseBack.barrisense_feedback_api.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/feedbacks") // La ruta base para todos los endpoints de feedbacks
public class FeedbackController {

    private final FeedbackService feedbackService;


    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Endpoint para obtener todos los feedbacks.
     * HTTP GET /api/feedbacks
     *
     * @return una lista de todos los feedbacks.
     */
    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.findAll();
    }

    // http://localhost:8080/api/feedbacks

    /**
     * Endpoint para obtener un feedback por su ID.
     * HTTP GET /api/feedbacks/{id}
     *
     * @param id El ID del feedback a buscar.
     * @return El feedback encontrado o un 404 Not Found si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.findById(id)
                .map(feedback -> ResponseEntity.ok(feedback))
                .orElse(ResponseEntity.notFound().build());
    }
// holi

    /**
     * Endpoint para obtener el número de quejas por ID de barrio.
     * HTTP GET /api/feedbacks/count/by-neighborhood/{hoodId}
     * @param hoodId El ID del barrio pasado en la URL.
     * @return Un objeto JSON con el ID del barrio y el total de quejas.
     */
    @GetMapping("/count/by-neighborhood/{hoodId}")
    public FeedbackCountDTO getComplaintCountByNeighborhood(@PathVariable Long hoodId) {
        return feedbackService.countComplaintsByHoodId(hoodId);
    }

    // http://localhost:8080/api/feedbacks/count/by-neighborhood/2


    /**
     * Endpoint para obtener todas las quejas de un barrio específico por su ID.
     * HTTP GET /api/feedbacks/by-neighborhood/{hoodId}
     * @param hoodId El ID del barrio pasado en la URL.
     * @return Una lista de objetos Feedback en formato JSON.
     */
    @GetMapping("/by-neighborhood/{hoodId}")
    public List<Feedback> getFeedbacksByNeighborhood(@PathVariable Long hoodId) {
        return feedbackService.findAllByHoodId(hoodId);
    }


    /**
     * Endpoint para obtener el número de quejas para todos los barrios.
     * HTTP GET /api/feedbacks/count/by-neighborhood/all
     * @return Una lista de objetos JSON, cada uno con un ID de barrio y su total de quejas.
     */
    @GetMapping("/count/by-neighborhood/all")
    public List<FeedbackCountDTO> getAllComplaintCountsByNeighborhood() {
        return feedbackService.countAllComplaintsByNeighborhood();
    }

    // http://localhost:8080/api/feedbacks/count/by-neighborhood/all


    /**
     * Endpoint para crear un nuevo feedback.
     * HTTP POST /api/feedbacks
     * @param feedback El objeto {@link Feedback} recibido en el cuerpo de la petición.
     * @return El feedback guardado, envuelto en una respuesta HTTP 200 OK.
     */
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.save(feedback);
        return ResponseEntity.ok(savedFeedback);
    }

}

