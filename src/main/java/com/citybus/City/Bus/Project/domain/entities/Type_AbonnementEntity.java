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
@Table(name = "Type_Abonnement")
public class Type_AbonnementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Type_Abonnemnt_id_seq")
    private int id_type_abonnement;
    private String nom_type_abonnement;
}
