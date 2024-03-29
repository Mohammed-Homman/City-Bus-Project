package com.citybus.City.Bus.Project.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Ligne")
public class LigneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ligne_id_seq")
    private int id;
    private String nom_ligne;
    private String description_ligne;
    private Double distance_ligne;

    @JsonIgnore
    @ManyToMany(mappedBy = "lignes")
    private List<AbonnementEntity> abonnements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ligne", cascade = CascadeType.ALL)
    private List<Bus_Entity> buss;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Chauffeur_Ligne", joinColumns = @JoinColumn(name = "ligne_id"),
            inverseJoinColumns = @JoinColumn(name = "chauffeur_id"))
    private List<ChauffeurEntity> chauffeurs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Ligne_Station",
            joinColumns = @JoinColumn(name = "ligne_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private List<Station_Entity> stations;
}
