package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Ligne")
public class LigneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ligne_id_seq")
    private int id;
    private String nom_ligne;
    private String description_ligne;
    private String couleur_ligne;
    private Double distance_ligne;
}
