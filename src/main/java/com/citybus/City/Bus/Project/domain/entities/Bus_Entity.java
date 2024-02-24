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
    private int id_bus;
    private String matricule;
    private String marque;
    private String modele;
    private int capacite;
    private LocalDate derniere_maintenance;
    private int nombre_de_reparation;
}
