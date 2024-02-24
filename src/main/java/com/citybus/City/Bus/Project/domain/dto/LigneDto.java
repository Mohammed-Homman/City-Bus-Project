package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneDto {
    private int id;
    private String nom_ligne;
    private String description_ligne;
    private String couleur_ligne;
    private Double distance_ligne;
}
