package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.HoraireEntity;
import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station_Dto {
    private int id;
    private String nom_station;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int ordre;
    private LigneEntity ligne;
    private List<HoraireEntity> horaire;
}
