package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.ArretEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArretRepository extends CrudRepository<ArretEntity, Integer> {
}
