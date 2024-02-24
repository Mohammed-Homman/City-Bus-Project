package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends CrudRepository<Bus_Entity, Integer> {
}
