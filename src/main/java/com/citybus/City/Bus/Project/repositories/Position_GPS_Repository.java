package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Position_GPS_Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Position_GPS_Repository extends CrudRepository<Position_GPS_Entity,Integer> {
}
