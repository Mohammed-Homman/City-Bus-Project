package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
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
@Entity
@Table(name = "Position_GPS")
public class Position_GPS_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_position;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime temps_de_localisation;
}
