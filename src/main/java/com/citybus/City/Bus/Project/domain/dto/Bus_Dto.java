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
public class Bus_Dto {
    private int id_bus;
    private String matricule;
    private String marque;
    private String modele;
    private int capacite;
    private LocalDate derniere_maintenance;
    private int nombre_de_reparation;

}
