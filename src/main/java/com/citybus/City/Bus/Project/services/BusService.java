package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;

import java.util.List;
import java.util.Optional;

public interface BusService {
    Bus_Entity save(Bus_Entity busEntity);

    List<Bus_Entity> findAll();

    Optional<Bus_Entity> findOne(int id);

    boolean isExists(int id);

    Bus_Entity partialUpdate(int id, Bus_Entity busEntity);

    void delete(int id);

    void updateBusType(int busId, int typeId);

    void updateBusStatut(int busId, int statutId);

    void updateBusPosition(int busId, int positionId);
}
