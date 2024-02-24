package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;

import java.util.List;
import java.util.Optional;

public interface AbonnementService {
    AbonnementEntity save(AbonnementEntity abonnementEntity);

    List<AbonnementEntity> findAll();

    boolean isExists(int id);

    Optional<AbonnementEntity> findOne(int id);

    void delete(int id);
}
