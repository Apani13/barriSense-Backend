package com.barriSenseBack.barrisense_feedback_api.controller;

import com.barriSenseBack.barrisense_feedback_api.dto.ComplaintCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Complaint;
import com.barriSenseBack.barrisense_feedback_api.service.ComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    private static final Logger logger = LoggerFactory.getLogger(ComplaintController.class);

    @Autowired
    public ComplaintController(ComplaintService complaintService) {

        this.complaintService = complaintService;
    }

    /**
     * Endpoint para obtener todas las quejas.
     * HTTP GET /api/complaints
     *
     * @return una lista de todas las quejas.
     */
    @GetMapping
    public List<Complaint> getAllComplaints() {
        ComplaintControllerLogEvent.REQUEST_GET_ALL.log(logger);
        return complaintService.findAll();
    }

    /**
     * Endpoint para obtener una queja por su ID.
     * HTTP GET /api/complaints/{id}
     *
     * @param id El ID de la queja a buscar.
     * @return La queja encontrada o un 404 Not Found si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id) {
        ComplaintControllerLogEvent.REQUEST_GET_BY_ID.log(logger, id);
        return complaintService.findById(id)
                .map(complaint -> ResponseEntity.ok(complaint))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para obtener el número de quejas por ID de barrio.
     * HTTP GET /api/complaints/count/by-neighborhood/{hoodId}
     * @param hoodId El ID del barrio pasado en la URL.
     * @return Un objeto JSON con el ID del barrio y el total de quejas.
     */
    @GetMapping("/count/by-neighborhood/{hoodId}")
    public ComplaintCountDTO getComplaintCountByNeighborhood(@PathVariable Long hoodId) {

        return complaintService.countComplaintsByHoodId(hoodId);
    }

    /**
     * Endpoint para obtener todas las quejas de un barrio específico por su ID.
     * HTTP GET /api/complaints/by-neighborhood/{hoodId}
     * @param hoodId El ID del barrio pasado en la URL.
     * @return Una lista de objetos Complaint en formato JSON.
     */
    @GetMapping("/by-neighborhood/{hoodId}")
    public List<Complaint> getComplaintsByNeighborhood(@PathVariable Long hoodId) {
        ComplaintControllerLogEvent.REQUEST_COUNT_BY_HOOD.log(logger, hoodId);
        return complaintService.findAllByHoodId(hoodId);
    }

    /**
     * Endpoint para obtener el número de quejas para todos los barrios.
     * HTTP GET /api/complaints/count/by-neighborhood/all
     * @return Una lista de objetos JSON, cada uno con un ID de barrio y su total de quejas.
     */
    @GetMapping("/count/by-neighborhood/all")
    public List<ComplaintCountDTO> getAllComplaintCountsByNeighborhood() {
        ComplaintControllerLogEvent.REQUEST_GET_ALL_COUNTS.log(logger);
        return complaintService.countAllComplaintsByNeighborhood();
    }

    /**
     * Endpoint para crear una nueva queja.
     * HTTP POST /api/complaints
     * @param complaint El objeto {@link Complaint} recibido en el cuerpo de la petición.
     * @return La queja guardada, envuelta en una respuesta HTTP 200 OK.
     */
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        ComplaintControllerLogEvent.REQUEST_CREATE.log(logger);
        Complaint savedComplaint = complaintService.save(complaint);
        return ResponseEntity.ok(savedComplaint);
    }
}