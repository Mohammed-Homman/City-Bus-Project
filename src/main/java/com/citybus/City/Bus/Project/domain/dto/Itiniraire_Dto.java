package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
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
public class Itiniraire_Dto {
    private int id;
    private Station_Entity station_depart;
    private  Station_Entity station_arrivee;
    private List<Station_Entity> stations_intermediaires;
    private List<LigneEntity> lignes;
}
