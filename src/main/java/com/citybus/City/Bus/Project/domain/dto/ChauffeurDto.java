package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import com.citybus.City.Bus.Project.domain.entities.Statut_ChauffeurEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private Statut_ChauffeurEntity statutChauffeurEntity;
    private List<LigneEntity> lignes;
}
