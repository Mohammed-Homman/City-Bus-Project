package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Statut_ChauffeurEntity;

import java.util.List;
import java.util.Optional;

public interface Statut_Chauffeur_Service {
    Statut_ChauffeurEntity save(Statut_ChauffeurEntity statutChauffeurEntity);

    List<Statut_ChauffeurEntity> findAll();

    Optional<Statut_ChauffeurEntity> findOne(int id);

    boolean isExists(int id);

    Statut_ChauffeurEntity partialUpdate(int id, Statut_ChauffeurEntity statutChauffeurEntity);

    void delete(int id);
}
