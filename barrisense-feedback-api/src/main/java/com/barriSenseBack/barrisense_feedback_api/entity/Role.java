package com.barriSenseBack.barrisense_feedback_api.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    @Column(name = "role_type", nullable = false, unique = true)
    private RoleType roleType;
}
