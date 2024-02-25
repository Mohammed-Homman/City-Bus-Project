package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Statut_ChauffeurDto {
    private int id;
    private String nom_satut_chauffeur;

}
