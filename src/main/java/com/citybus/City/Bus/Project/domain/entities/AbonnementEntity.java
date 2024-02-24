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
@Table(name = "Abonnement")
public class AbonnementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double prix;
}
