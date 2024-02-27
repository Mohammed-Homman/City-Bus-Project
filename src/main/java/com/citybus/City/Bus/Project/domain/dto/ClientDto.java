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
public class ClientDto {
    private String id;
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
}
