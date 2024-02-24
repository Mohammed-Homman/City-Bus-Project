package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneRepository extends CrudRepository<LigneEntity, Integer> {
}
