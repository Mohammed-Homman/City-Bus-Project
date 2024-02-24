package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Station")
public class Station_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_id_seq")
    private int station_id;
    private String nom_station;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
