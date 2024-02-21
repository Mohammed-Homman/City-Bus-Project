package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Statut_Bus_Dto {
    private int id_statut_bus;
    private String nom_statut_bus;

}
