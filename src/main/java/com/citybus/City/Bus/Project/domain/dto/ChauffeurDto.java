package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChauffeurDto {
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
