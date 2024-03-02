package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.Point_de_venteEntity;

import java.util.List;
import java.util.Optional;

public interface Point_de_venteService {
    Point_de_venteEntity save(Point_de_venteEntity pointDeVenteEntity);

    List<Point_de_venteEntity> findAll();

    Optional<Point_de_venteEntity> findOne(int id);

    boolean isExists(int id);

    Point_de_venteEntity partialUpdate(int id, Point_de_venteEntity pointDeVenteEntity);

    void delete(int id);
}
