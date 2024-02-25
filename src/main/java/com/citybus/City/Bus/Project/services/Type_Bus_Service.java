package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Type_Bus_Entity;

import java.util.List;
import java.util.Optional;

public interface Type_Bus_Service {
    Type_Bus_Entity save(Type_Bus_Entity typeBusEntity);

    List<Type_Bus_Entity> findAll();

    Optional<Type_Bus_Entity> findOne(int id);

    boolean isExists(int id);

    void delete(int id);

    Type_Bus_Entity partialUpdate(Type_Bus_Entity typeBusEntity);
}
