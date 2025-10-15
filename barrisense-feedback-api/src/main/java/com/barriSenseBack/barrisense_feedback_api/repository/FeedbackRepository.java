package com.barriSenseBack.barrisense_feedback_api.repository;


import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {


    /**
     * Cuenta el número de feedbacks (quejas) para un ID de barrio específico.
     * Spring Data JPA entiende "countBy" y "HoodId" y crea la consulta:
     * SELECT count(*) FROM feedbacks WHERE hood_id = ?
     * @param hoodId El ID del barrio.
     * @return el número de quejas.
     */
    long countByHoodId(Long hoodId);

    /**
     * Busca y devuelve una lista de todos los feedbacks para un ID de barrio específico.
     * Spring Data JPA entiende "findBy" y "HoodId" y crea la consulta:
     * SELECT * FROM feedbacks WHERE hood_id = ?
     * @param hoodId El ID del barrio.
     * @return una lista de las quejas (objetos Feedback).
     */
    List<Feedback> findByHoodId(Long hoodId);
}
