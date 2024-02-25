package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Statut_Bus_Entity;
import com.citybus.City.Bus.Project.repositories.Statut_Bus_Repository;
import com.citybus.City.Bus.Project.services.Statut_Bus_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Statut_Bus_ServiceImpl implements Statut_Bus_Service {
    private Statut_Bus_Repository statutBusRepository;

    public Statut_Bus_ServiceImpl(Statut_Bus_Repository statutBusRepository) {
        this.statutBusRepository = statutBusRepository;
    }

    @Override
    public Statut_Bus_Entity save(Statut_Bus_Entity statutBusEntity) {
        return statutBusRepository.save(statutBusEntity);
    }

    @Override
    public List<Statut_Bus_Entity> findAll() {
        return StreamSupport.stream(statutBusRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Statut_Bus_Entity> findOne(int id) {
        return statutBusRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return statutBusRepository.existsById(id);
    }

    @Override
    public Statut_Bus_Entity partialUpdate(int id, Statut_Bus_Entity statutBusEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        statutBusRepository.deleteById(id);
    }
}
