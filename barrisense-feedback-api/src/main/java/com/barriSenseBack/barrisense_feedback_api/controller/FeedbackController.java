package com.barriSenseBack.barrisense_feedback_api.controller;


import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import com.barriSenseBack.barrisense_feedback_api.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    // /api/feedbacks/post

}

