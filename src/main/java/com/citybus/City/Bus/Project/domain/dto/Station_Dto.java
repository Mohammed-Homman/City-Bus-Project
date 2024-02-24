package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station_Dto {
    private int id;
    private String nom_station;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
