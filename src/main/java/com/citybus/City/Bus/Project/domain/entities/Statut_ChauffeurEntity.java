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
@Table(name = "Satut_Chauffeur")
public class Statut_ChauffeurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statut_chauffeur_id_seq")
    private int id_satut_chauffeur;
    private String nom_satut_chauffeur;
}
