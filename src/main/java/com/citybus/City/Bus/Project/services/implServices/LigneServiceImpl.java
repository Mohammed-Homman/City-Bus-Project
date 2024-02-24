package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import com.citybus.City.Bus.Project.repositories.LigneRepository;
import com.citybus.City.Bus.Project.services.LigneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LigneServiceImpl implements LigneService {
    private LigneRepository ligneRepository;

    public LigneServiceImpl(LigneRepository ligneRepository) {
        this.ligneRepository = ligneRepository;
    }

    @Override
    public LigneEntity save(LigneEntity ligneEntity) {
        return ligneRepository.save(ligneEntity);
    }

    @Override
    public List<LigneEntity> findAll() {
        return StreamSupport.stream(ligneRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<LigneEntity> findOne(int id) {
        return ligneRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return ligneRepository.existsById(id);
    }

    @Override
    public LigneEntity partialUpdate(int id, LigneEntity ligneEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        ligneRepository.deleteById(id);
    }
}
