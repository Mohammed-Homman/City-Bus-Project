package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Statut_AbonnementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Statut_Abonnement_Repository extends CrudRepository<Statut_AbonnementEntity, Integer> {
}
