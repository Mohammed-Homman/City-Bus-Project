package com.citybus.City.Bus.Project.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "lignes")
    private List<AbonnementEntity> abonnements;
}
