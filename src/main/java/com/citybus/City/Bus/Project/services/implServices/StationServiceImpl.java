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
        return null;
    }

    @Override
    public void delete(int id) {
        stationRepository.deleteById(id);
    }
}
