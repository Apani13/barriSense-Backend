package com.barriSenseBack.barrisense_feedback_api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder // Mantenemos el Builder, es demasiado útil para los tests
@NoArgsConstructor // Constructor sin argumentos para JPA
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String username;

    @Column(nullable = false, unique = true)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    // Role stuff
    // @ManyToMany(...)
    // private Set<Role> roles;




}

