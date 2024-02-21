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
@Table(name = "Statut_Abonnement")
public class Statut_AbonnementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Statut_Abonnemnt_id_seq")
    private int id_statut_abonnement;
    private String nom_statut_abonnement;
}
