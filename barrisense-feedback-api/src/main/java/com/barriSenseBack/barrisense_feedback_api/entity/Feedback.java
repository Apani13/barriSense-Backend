package com.barriSenseBack.barrisense_feedback_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Crea todos los Getters, Setters, toString, equals, etc.
@NoArgsConstructor // Crea un constructor vacío (public Feedback() {})
@AllArgsConstructor // Crea un constructor con TODOS los campos
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "hood_id", nullable = false)
    private Long hoodId;

    @Column(name = "hood_name", nullable = false)
    private String hoodName;

    @Column(nullable = false, length = 1000)
    private String content;


}