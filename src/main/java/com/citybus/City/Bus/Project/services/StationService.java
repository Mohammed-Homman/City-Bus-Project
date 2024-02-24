package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Station_Entity;

import java.util.List;
import java.util.Optional;

public interface StationService {
    Station_Entity save(Station_Entity stationEntity);

    List<Station_Entity> findAll();

    Optional<Station_Entity> findOne(int id);

    boolean isExists(int id);

    Station_Entity partialUpdate(int id, Station_Entity stationEntity);

    void delete(int id);
}
