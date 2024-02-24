package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;

import java.util.List;
import java.util.Optional;

public interface ChauffeurService {
    ChauffeurEntity save(ChauffeurEntity chauffeurEntity);

    List<ChauffeurEntity> findAll();

    Optional<ChauffeurEntity> findOne(int id);

    boolean isExists(int id);

    ChauffeurEntity partialUpdate(int id, ChauffeurEntity chauffeurEntity);

    void delete(int id);
}
