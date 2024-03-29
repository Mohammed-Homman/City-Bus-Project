package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Station")
public class Station_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nom_station;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int ordre;

    @ManyToOne(cascade = CascadeType.ALL)
    private LigneEntity ligne;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HoraireEntity> horaire;
}
