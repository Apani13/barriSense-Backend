package com.barriSenseBack.barrisense_feedback_api.config;

import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import com.barriSenseBack.barrisense_feedback_api.entity.Neighborhood;
import com.barriSenseBack.barrisense_feedback_api.repository.FeedbackRepository;
import com.barriSenseBack.barrisense_feedback_api.repository.NeighborhoodRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Componente que se ejecuta al inicio de la aplicación para poblar la base de datos con datos de prueba.
 * <p>
 * Implementa {@link CommandLineRunner}, lo que garantiza que el método {@code run} se ejecute una sola vez
 * después de que el contexto de la aplicación se haya cargado.
 * Su principal responsabilidad es crear un conjunto de feedbacks (quejas) de ejemplo si la base de datos está vacía,
 * asegurando un entorno de desarrollo con datos realistas.
 *
 * @author El equipo de BarriSense
 * @since 2025-10-15
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final FeedbackRepository feedbackRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    @Autowired
    public DataInitializer(FeedbackRepository feedbackRepository, NeighborhoodRepository neighborhoodRepository) {
        this.feedbackRepository = feedbackRepository;
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (feedbackRepository.count() > 0) {
            log.info("La base de datos de feedbacks ya está poblada. No se crearán nuevos datos.");
            return;
        }

        log.info("Poblando la base de datos con feedbacks de prueba...");

        List<Neighborhood> neighborhoods = neighborhoodRepository.findAll();
        if (neighborhoods.isEmpty()) {
            log.warn("No se encontraron barrios en la base de datos. Asegúrate de que data.sql se ha ejecutado.");
            return;
        }

        List<String> quejas = Arrays.asList(
                "Demasiado ruido de turistas por la noche.", "Las calles están muy sucias.",
                "Imposible caminar por la acera, siempre bloqueada.", "Los precios en las tiendas son abusivos.",
                "Patinetes eléctricos de alquiler por todas partes, son un peligro.", "Falta de respeto en las zonas comunes.",
                "Fiestas en pisos turísticos hasta altas horas.", "El comercio local desaparece."
        );


        List<Long> hotspotIds = Arrays.asList(1L, 2L, 3L, 4L, 6L, 31L);

        int numeroDeFeedbacksACrear = 300;
        Random random = new Random();

        for (int i = 0; i < numeroDeFeedbacksACrear; i++) {
            Neighborhood barrioSeleccionado;


            if (random.nextInt(10) < 7) {
                Long hotspotId = hotspotIds.get(random.nextInt(hotspotIds.size()));
                barrioSeleccionado = neighborhoods.stream().filter(n -> n.getId() == hotspotId).findFirst().orElse(neighborhoods.get(0));
            } else {
                barrioSeleccionado = neighborhoods.get(random.nextInt(neighborhoods.size()));
            }

            Long userIdRandom = (long) (random.nextInt(50) + 1);
            String quejaRandom = quejas.get(random.nextInt(quejas.size()));

            Feedback feedback = new Feedback(userIdRandom, barrioSeleccionado.getId(), barrioSeleccionado.getName(), quejaRandom);
            feedbackRepository.save(feedback);
        }

        log.info("¡Se han creado {} feedbacks de prueba!", numeroDeFeedbacksACrear);
    }
}