package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
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
public class ClientDto {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private String emploi;
    private String cni;
    private String mot_de_passe;
    private LocalDate dateNaissance;
    private String sex;
    private LocalDate dateInscription;
    private List<AbonnementEntity> abonnements;
}
