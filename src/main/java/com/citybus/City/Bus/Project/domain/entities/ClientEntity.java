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
@Table(name = "Client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private String emploi;
    private String cni;
    private String mot_de_passe;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    private String sex;

    @Column(name = "date_inscription")
    private LocalDate dateInscription;
}
