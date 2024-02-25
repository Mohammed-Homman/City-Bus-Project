package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Chauffeur")
public class ChauffeurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom_chauffeur;
    private String prenom_chauffeur;
    private LocalDate date_naissance;
    private String adresse;
    private String telephone;
    private String email;
    private LocalDate date_recrutement;
    private Double salaire;
}
