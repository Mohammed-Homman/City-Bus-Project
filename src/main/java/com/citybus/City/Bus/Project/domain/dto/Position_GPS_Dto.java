package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position_GPS_Dto {
    private int id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime temps_de_localisation;

}
