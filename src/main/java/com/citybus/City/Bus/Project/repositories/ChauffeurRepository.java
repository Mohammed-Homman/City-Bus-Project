package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChauffeurRepository extends CrudRepository<ChauffeurEntity, Integer> {
}
