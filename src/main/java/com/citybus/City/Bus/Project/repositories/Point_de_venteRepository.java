package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.Point_de_venteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Point_de_venteRepository extends CrudRepository<Point_de_venteEntity,Integer> {
}
