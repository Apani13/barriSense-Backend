package com.barriSenseBack.barrisense_feedback_api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "barrios") // Nombramos la tabla "barrios" para que coincida con tu data.sql
public class Neighborhood {

    @Id
    // No usamos @GeneratedValue porque los IDs los estás poniendo tú en data.sql
    private Long id;

    @Column(name = "nombre") // Nombramos la columna "nombre" para que coincida con tu data.sql
    private String name;

    private String district;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "neighborhood_postal_codes", joinColumns = @JoinColumn(name = "neighborhood_id"))
    @Column(name = "postal_code")
    private List<Long> cp;

    // Constructor vacío requerido por JPA
    public Neighborhood() {
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Long> getCp() {
        return cp;
    }

    public void setCp(List<Long> cp) {
        this.cp = cp;
    }
}
