package com.citybus.City.Bus.Project.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Client_Abonnement", joinColumns = @JoinColumn(name = "abonnement_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<ClientEntity> clients;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_abonnement_id")
    private Type_AbonnementEntity typeAbonnementEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statut_abonnement_id")
    private Statut_AbonnementEntity statutAbonnementEntity;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Ligne_Abonnement", joinColumns = @JoinColumn(name = "abonnement_id"),
            inverseJoinColumns = @JoinColumn(name = "ligne_id"))
    private List<LigneEntity> lignes;
}
