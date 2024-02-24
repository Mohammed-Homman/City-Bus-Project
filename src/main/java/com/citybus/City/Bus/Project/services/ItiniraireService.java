package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Itiniraire_Entity;

import java.util.List;
import java.util.Optional;

public interface ItiniraireService {
    Itiniraire_Entity save(Itiniraire_Entity itiniraireEntity);

    List<Itiniraire_Entity> findAll();

    Optional<Itiniraire_Entity> findOne(int id);

    boolean isExists(int id);

    Itiniraire_Entity partialUpdate(int id, Itiniraire_Entity itiniraireEntity);

    void delete(int id);
}
