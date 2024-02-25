package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Statut_Bus_Entity;

import java.util.List;
import java.util.Optional;

public interface Statut_Bus_Service {
    Statut_Bus_Entity save(Statut_Bus_Entity statutBusEntity);

    List<Statut_Bus_Entity> findAll();

    Optional<Statut_Bus_Entity> findOne(int id);

    boolean isExists(int id);

    Statut_Bus_Entity partialUpdate(int id, Statut_Bus_Entity statutBusEntity);

    void delete(int id);
}
