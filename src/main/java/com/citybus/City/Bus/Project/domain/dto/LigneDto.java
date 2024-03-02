package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;
import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;
import com.citybus.City.Bus.Project.domain.entities.Station_Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneDto {
    private int id;
    private String nom_ligne;
    private String description_ligne;
    private Double distance_ligne;
    private List<AbonnementEntity> abonnements;
    private List<Bus_Entity> buss;
    private List<ChauffeurEntity> chauffeurs;
    private List<Station_Entity> stations;
}
