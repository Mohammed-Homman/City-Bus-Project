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
@Table(name = "Arret")
public class ArretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arret_id_seq")
    private int id_arret;
    private int ordre_arret;
}
