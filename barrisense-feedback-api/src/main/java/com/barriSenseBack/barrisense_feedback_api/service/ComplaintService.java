package com.barriSenseBack.barrisense_feedback_api.service;

import com.barriSenseBack.barrisense_feedback_api.dto.ComplaintCountDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Complaint;
import com.barriSenseBack.barrisense_feedback_api.repository.ComplaintRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private static final Logger logger = LoggerFactory.getLogger(ComplaintService.class);
    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    /**
     * Recupera todas las quejas de la base de datos.
     * @return una lista de quejas.
     */
    public List<Complaint> findAll() {
        ComplaintServiceLogEvent.FETCHING_ALL.log(logger);
        return complaintRepository.findAll();
    }

    /**
     * Busca una queja por su ID.
     * @param id El ID de la queja a buscar.
     * @return un Optional que contiene la queja si se encuentra, o un Optional vacío si no.
     */
    public Optional<Complaint> findById(Long id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if (complaint.isPresent()) {
            ComplaintServiceLogEvent.FOUND_BY_ID.log(logger, id);
        } else {
            ComplaintServiceLogEvent.NOT_FOUND_BY_ID.log(logger, id);
        }
        return complaint;
    }

    /**
     * Calcula el número de quejas para un barrio y devuelve el resultado en un DTO.
     * @param hoodId El ID del barrio.
     * @return un DTO con el ID del barrio y el total de quejas.
     */
    public ComplaintCountDTO countComplaintsByHoodId(Long hoodId) {
        ComplaintServiceLogEvent.COUNTING_BY_HOOD_ID.log(logger, hoodId);
        long count = complaintRepository.countByHoodId(hoodId);
        return new ComplaintCountDTO(hoodId, count);
    }

    /**
     * Devuelve todas las quejas asociadas a un ID de barrio.
     * @param hoodId El ID del barrio.
     * @return una lista de objetos Complaint.
     */
    public List<Complaint> findAllByHoodId(Long hoodId) {
        ComplaintServiceLogEvent.FETCHING_BY_HOOD_ID.log(logger, hoodId);
        return complaintRepository.findByHoodId(hoodId);
    }

    /**
     * Devuelve una lista con el total de quejas para cada barrio.
     * @return una lista de objetos ComplaintCountDTO.
     */
    public List<ComplaintCountDTO> countAllComplaintsByNeighborhood() {
        // Este método podría tener su propio evento de log si fuera necesario,
        // pero por ahora lo dejamos así por simplicidad.
        return complaintRepository.countAllGroupByHoodId();
    }

    /**
     * Guarda una nueva queja en la base de datos.
     * @param complaint La queja a guardar.
     * @return la queja guardada con su ID asignado.
     */
    public Complaint save(Complaint complaint) {
        Complaint savedComplaint = complaintRepository.save(complaint);
        ComplaintServiceLogEvent.COMPLAINT_SAVED.log(logger, savedComplaint.getId());
        return savedComplaint;
    }
}