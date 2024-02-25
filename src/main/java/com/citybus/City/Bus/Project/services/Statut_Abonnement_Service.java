package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Statut_AbonnementEntity;

import java.util.List;
import java.util.Optional;

public interface Statut_Abonnement_Service {
    Statut_AbonnementEntity save(Statut_AbonnementEntity statutAbonnementEntity);

    List<Statut_AbonnementEntity> findAll();

    Optional<Statut_AbonnementEntity> findOne(int id);

    boolean isExists(int id);

    Statut_AbonnementEntity partialUpdate(int id, Statut_AbonnementEntity statutAbonnementEntity);

    void delete(int id);
}
