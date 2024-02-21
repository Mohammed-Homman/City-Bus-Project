package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Statut_Bus")

public class Statut_Bus_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statut_bus_id_seq")
    private int id_statut_bus;
    private String nom_statut_bus;
}
