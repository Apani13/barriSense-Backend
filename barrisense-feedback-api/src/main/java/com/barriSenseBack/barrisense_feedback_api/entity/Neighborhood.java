package com.barriSenseBack.barrisense_feedback_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barrios")
public class Neighborhood {

    @Id
    @Getter
    @Setter
    private Long id;

    @Column(name = "nombre")
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String district;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "neighborhood_postal_codes", joinColumns = @JoinColumn(name = "neighborhood_id"))
    @Column(name = "postal_code")
    @Getter @Setter
    private List<Long> cp;



}
