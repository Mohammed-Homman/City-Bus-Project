package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Itiniraire_Entity;
import com.citybus.City.Bus.Project.repositories.ItiniraireRepository;
import com.citybus.City.Bus.Project.services.ItiniraireService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItiniraireServiceImpl implements ItiniraireService {
    private ItiniraireRepository itiniraireRepository;

    public ItiniraireServiceImpl(ItiniraireRepository itiniraireRepository) {
        this.itiniraireRepository = itiniraireRepository;
    }

    @Override
    public Itiniraire_Entity save(Itiniraire_Entity itiniraireEntity) {
        return itiniraireRepository.save(itiniraireEntity);
    }

    @Override
    public List<Itiniraire_Entity> findAll() {
        return StreamSupport.stream(itiniraireRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Itiniraire_Entity> findOne(int id) {
        return itiniraireRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return itiniraireRepository.existsById(id);
    }

    @Override
    public Itiniraire_Entity partialUpdate(int id, Itiniraire_Entity itiniraireEntity) {
        itiniraireEntity.setId(id);

        return itiniraireRepository.findById(id).map(existingItiniraire -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(itiniraireEntity.getStation_depart()).ifPresent(existingItiniraire::setStation_depart);
            Optional.ofNullable(itiniraireEntity.getStation_arrivee()).ifPresent(existingItiniraire::setStation_arrivee);
            Optional.ofNullable(itiniraireEntity.getStations_intermediaires()).ifPresent(existingItiniraire::setStations_intermediaires);
            Optional.ofNullable(itiniraireEntity.getLignes()).ifPresent(existingItiniraire::setLignes);

            // Save and return the updated entity
            return itiniraireRepository.save(existingItiniraire);
        }).orElseThrow(() -> new RuntimeException("Itiniraire does not exist"));
    }


    @Override
    public void delete(int id) {
        itiniraireRepository.deleteById(id);
    }
}
