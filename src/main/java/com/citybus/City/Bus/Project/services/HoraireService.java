package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.HoraireEntity;

import java.util.List;
import java.util.Optional;

public interface HoraireService {
    HoraireEntity save(HoraireEntity horaireEntity);

    List<HoraireEntity> findAll();

    Optional<HoraireEntity> findOne(int id);

    boolean isExists(int id);

    HoraireEntity partialUpdate(int id, HoraireEntity horaireEntity);

    void delete(int id);
}
