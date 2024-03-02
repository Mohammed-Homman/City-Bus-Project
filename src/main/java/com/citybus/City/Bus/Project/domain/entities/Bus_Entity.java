package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Bus")
public class Bus_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String matricule;
    private String marque;
    private String modele;
    private int capacite;
    private LocalDate derniere_maintenance;
    private int nombre_de_reparation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_bus_id")
    private Type_Bus_Entity typeBusEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statut_bus_id")
    private Statut_Bus_Entity statutBusEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_bus_id")
    private Position_GPS_Entity positionGpsEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chauffeur_id")
    private ChauffeurEntity chauffeur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ligne_id")
    private LigneEntity ligne;
}
