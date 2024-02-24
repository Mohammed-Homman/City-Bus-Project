package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Position_GPS_Entity;
import com.citybus.City.Bus.Project.repositories.Position_GPS_Repository;
import com.citybus.City.Bus.Project.services.Position_GPS_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Position_GPS_ServiceImpl implements Position_GPS_Service {
    private Position_GPS_Repository positionGpsRepository;

    public Position_GPS_ServiceImpl(Position_GPS_Repository positionGpsRepository) {
        this.positionGpsRepository = positionGpsRepository;
    }

    @Override
    public Position_GPS_Entity save(Position_GPS_Entity positionGpsEntity) {
        return positionGpsRepository.save(positionGpsEntity);
    }

    @Override
    public List<Position_GPS_Entity> findAll() {
        return StreamSupport.stream(positionGpsRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Position_GPS_Entity> findOne(int id) {
        return positionGpsRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return positionGpsRepository.existsById(id);
    }

    @Override
    public Position_GPS_Entity partialUpdate(int id, Position_GPS_Entity positionGpsEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        positionGpsRepository.deleteById(id);
    }
}
