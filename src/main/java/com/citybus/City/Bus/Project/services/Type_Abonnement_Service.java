package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;

import java.util.List;
import java.util.Optional;

public interface Type_Abonnement_Service {
    Type_AbonnementEntity save(Type_AbonnementEntity typeAbonnementEntity);

    List<Type_AbonnementEntity> findAll();

    Optional<Type_AbonnementEntity> findOne(int id);

    boolean isExists(int id);

    void delete(int id);

    Type_AbonnementEntity partialUpdate(Type_AbonnementEntity typeAbonnementEntity);
}
