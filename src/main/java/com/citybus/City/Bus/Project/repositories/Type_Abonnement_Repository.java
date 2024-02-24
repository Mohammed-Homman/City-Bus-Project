package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Type_Abonnement_Repository extends CrudRepository<Type_AbonnementEntity, Integer> {
}
