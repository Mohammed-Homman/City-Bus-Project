package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Station_Entity;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<Station_Entity, Integer> {
}