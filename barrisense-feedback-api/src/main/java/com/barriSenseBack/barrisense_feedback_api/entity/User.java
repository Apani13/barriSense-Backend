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
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter @Setter
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        this.roles.add(role);

        // Role stuff
        // @ManyToMany(...)
        // private Set<Role> roles;


    }

}

