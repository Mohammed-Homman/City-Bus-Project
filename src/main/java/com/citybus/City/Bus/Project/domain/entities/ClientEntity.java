package com.citybus.City.Bus.Project.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate dateNaissance;
    private String sex;
    private LocalDate dateInscription;

    @JsonIgnore
    @ManyToMany(mappedBy = "clients")
    private List<AbonnementEntity> abonnements;
}
