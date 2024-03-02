package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Point_de_venteEntity;
import com.citybus.City.Bus.Project.repositories.Point_de_venteRepository;
import com.citybus.City.Bus.Project.services.Point_de_venteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class Point_de_venteServiceImpl implements Point_de_venteService {

    private Point_de_venteRepository pointDeVenteRepository;

    public Point_de_venteServiceImpl(Point_de_venteRepository pointDeVenteRepository) {
        this.pointDeVenteRepository = pointDeVenteRepository;
    }

    @Override
    public Point_de_venteEntity save(Point_de_venteEntity pointDeVenteEntity) {
        return pointDeVenteRepository.save(pointDeVenteEntity);
    }

    @Override
    public List<Point_de_venteEntity> findAll() {
        return StreamSupport.stream(pointDeVenteRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Point_de_venteEntity> findOne(int id) {
        return pointDeVenteRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return pointDeVenteRepository.existsById(id);
    }

    @Override
    public Point_de_venteEntity partialUpdate(int id, Point_de_venteEntity pointDeVenteEntity) {
        pointDeVenteEntity.setId(id);

        return pointDeVenteRepository.findById(id).map(existingPointDeVente -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(pointDeVenteEntity.getNom()).ifPresent(existingPointDeVente::setNom);
            Optional.ofNullable(pointDeVenteEntity.getLatitude()).ifPresent(existingPointDeVente::setLatitude);
            Optional.ofNullable(pointDeVenteEntity.getLongitude()).ifPresent(existingPointDeVente::setLongitude);

            // Save and return the updated entity
            return pointDeVenteRepository.save(existingPointDeVente);
        }).orElseThrow(() -> new RuntimeException("Point de vente does not exist"));
    }


    @Override
    public void delete(int id) {
        pointDeVenteRepository.deleteById(id);
    }
}
