package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Statut_Bus_Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Statut_Bus_Repository extends CrudRepository<Statut_Bus_Entity, Integer> {
}
