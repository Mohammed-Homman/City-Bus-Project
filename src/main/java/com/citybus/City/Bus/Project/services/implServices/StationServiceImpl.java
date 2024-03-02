package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Station_Entity;
import com.citybus.City.Bus.Project.repositories.StationRepository;
import com.citybus.City.Bus.Project.services.StationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StationServiceImpl implements StationService {
    private StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station_Entity save(Station_Entity stationEntity) {
        return stationRepository.save(stationEntity);
    }

    @Override
    public List<Station_Entity> findAll() {
        return StreamSupport.stream(stationRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Station_Entity> findOne(int id) {
        return stationRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return stationRepository.existsById(id);
    }

    @Override
    public Station_Entity partialUpdate(int id, Station_Entity stationEntity) {
        stationEntity.setId(id);

        return stationRepository.findById(id).map(existingStation -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(stationEntity.getNom_station()).ifPresent(existingStation::setNom_station);
            Optional.ofNullable(stationEntity.getLatitude()).ifPresent(existingStation::setLatitude);
            Optional.ofNullable(stationEntity.getLongitude()).ifPresent(existingStation::setLongitude);
            Optional.ofNullable(stationEntity.getOrdre()).ifPresent(existingStation::setOrdre);
            Optional.ofNullable(stationEntity.getLigne()).ifPresent(existingStation::setLigne);
            Optional.ofNullable(stationEntity.getHoraire()).ifPresent(existingStation::setHoraire);

            // Save and return the updated entity
            return stationRepository.save(existingStation);
        }).orElseThrow(() -> new RuntimeException("Station_Entity does not exist"));
    }


    @Override
    public void delete(int id) {
        stationRepository.deleteById(id);
    }
}
