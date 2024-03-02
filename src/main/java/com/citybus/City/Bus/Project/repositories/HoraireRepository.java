package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.HoraireEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraireRepository extends CrudRepository<HoraireEntity,Integer> {
}
