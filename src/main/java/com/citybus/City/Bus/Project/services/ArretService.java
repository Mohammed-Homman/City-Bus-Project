package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.ArretEntity;

import java.util.List;
import java.util.Optional;

public interface ArretService {
    ArretEntity save(ArretEntity arretEntity);

    List<ArretEntity> findAll();

    boolean isExists(int id);

    ArretEntity partialUpdate(int id, ArretEntity arretEntity);

    void delete(int id);

    Optional<ArretEntity> findOne(int id);
}
