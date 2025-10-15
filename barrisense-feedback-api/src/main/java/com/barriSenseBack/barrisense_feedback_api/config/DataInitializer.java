//package com.barriSenseBack.barrisense_feedback_api.config;
//
//import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
//import com.barriSenseBack.barrisense_feedback_api.repository.FeedbackRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
//
//    private final FeedbackRepository feedbackRepository;
//
//    @Autowired
//    public DataInitializer(FeedbackRepository feedbackRepository) {
//        this.feedbackRepository = feedbackRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        log.info("Comenzando la inicialización de datos de feedback...");
//
//        // Lista de quejas de ejemplo
//        List<String> quejas = Arrays.asList(
//                "Demasiado ruido de turistas por la noche.",
//                "Las calles están muy sucias por los grupos grandes.",
//                "Imposible caminar por la acera, siempre está bloqueada.",
//                "Los precios en las tiendas de la zona son abusivos.",
//                "Los patinetes eléctricos de alquiler son un peligro.",
//                "Falta de respeto en las zonas comunes y parques.",
//                "Fiestas en pisos turísticos hasta altas horas.",
//                "Todo está enfocado al turista, el comercio local desaparece."
//        );
//
//        // Ya tienes 73 barrios cargados desde data.sql (IDs del 1 al 73)
//        int numeroDeBarrios = 73;
//        int numeroDeFeedbacksACrear = 200;
//        Random random = new Random();
//
//        for (int i = 0; i < numeroDeFeedbacksACrear; i++) {
//            // Seleccionamos datos al azar
//            Long userIdRandom = (long) (random.nextInt(50) + 1); // IDs de usuario del 1 al 50
//            Long hoodIdRandom = (long) (random.nextInt(numeroDeBarrios) + 1); // IDs de barrio del 1 al 73
//            String hoodNameRandom = "Barrio #" + hoodIdRandom; // Nombre de ejemplo
//            String quejaRandom = quejas.get(random.nextInt(quejas.size()));
//
//            // Creamos la nueva entidad de Feedback
//            Feedback feedback = new Feedback(userIdRandom, hoodIdRandom, hoodNameRandom, quejaRandom);
//
//            feedbackRepository.save(feedback);
//        }
//
//        log.info("¡Se han creado {} feedbacks de prueba!", numeroDeFeedbacksACrear);
//    }
//}