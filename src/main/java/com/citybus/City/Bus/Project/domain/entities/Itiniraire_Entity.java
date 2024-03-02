package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Itiniraire")
public class Itiniraire_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_depart_id")
    private Station_Entity station_depart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_arrivee_id")
    private  Station_Entity station_arrivee;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Station_Entity> stations_intermediaires;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneEntity> lignes;
}
