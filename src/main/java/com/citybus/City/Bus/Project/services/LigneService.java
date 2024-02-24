package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;

import java.util.List;
import java.util.Optional;

public interface LigneService {
    LigneEntity save(LigneEntity ligneEntity);

    List<LigneEntity> findAll();

    Optional<LigneEntity> findOne(int id);

    boolean isExists(int id);

    LigneEntity partialUpdate(int id, LigneEntity ligneEntity);

    void delete(int id);
}
