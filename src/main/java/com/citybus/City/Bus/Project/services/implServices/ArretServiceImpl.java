package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.ArretEntity;
import com.citybus.City.Bus.Project.repositories.ArretRepository;
import com.citybus.City.Bus.Project.services.ArretService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArretServiceImpl implements ArretService {
    private ArretRepository arretRepository;

    public ArretServiceImpl(ArretRepository arretRepository) {
        this.arretRepository = arretRepository;
    }

    @Override
    public ArretEntity save(ArretEntity arretEntity) {
        return arretRepository.save(arretEntity);
    }

    @Override
    public List<ArretEntity> findAll() {
        return StreamSupport.stream(arretRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public boolean isExists(int id) {
        return arretRepository.existsById(id);
    }

    @Override
    public ArretEntity partialUpdate(int id, ArretEntity arretEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        arretRepository.deleteById(id);
    }

    @Override
    public Optional<ArretEntity> findOne(int id) {
        return arretRepository.findById(id);
    }
}
