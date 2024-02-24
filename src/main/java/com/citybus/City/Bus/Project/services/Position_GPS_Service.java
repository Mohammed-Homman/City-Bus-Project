package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Position_GPS_Entity;

import java.util.List;
import java.util.Optional;

public interface Position_GPS_Service {
    Position_GPS_Entity save(Position_GPS_Entity positionGpsEntity);

    List<Position_GPS_Entity> findAll();

    Optional<Position_GPS_Entity> findOne(int id);

    boolean isExists(int id);

    Position_GPS_Entity partialUpdate(int id, Position_GPS_Entity positionGpsEntity);

    void delete(int id);
}
