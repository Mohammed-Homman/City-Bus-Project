package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.domain.entities.Statut_AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;
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
public class AbonnementDto {
    private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double prix;
    private List<ClientEntity> clients;
    private Type_AbonnementEntity typeAbonnement;
    private Statut_AbonnementEntity statutAbonnementEntity;
}
