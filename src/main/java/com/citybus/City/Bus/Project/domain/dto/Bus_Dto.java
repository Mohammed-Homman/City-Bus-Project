package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.entities.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bus_Dto {
    private int id;
    private String matricule;
    private String marque;
    private String modele;
    private int capacite;
    private LocalDate derniere_maintenance;
    private int nombre_de_reparation;
    private Type_Bus_Entity typeBusEntity;
    private Statut_Bus_Entity statutBusEntity;
    private Position_GPS_Entity positionGpsEntity;
    private ChauffeurEntity chauffeur;
    private LigneEntity ligne;
}
