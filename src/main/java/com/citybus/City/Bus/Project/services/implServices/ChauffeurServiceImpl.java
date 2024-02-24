package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;
import com.citybus.City.Bus.Project.repositories.ChauffeurRepository;
import com.citybus.City.Bus.Project.services.ChauffeurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChauffeurServiceImpl implements ChauffeurService {
    private ChauffeurRepository chauffeurRepository;

    public ChauffeurServiceImpl(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }

    @Override
    public ChauffeurEntity save(ChauffeurEntity chauffeurEntity) {
        return chauffeurRepository.save(chauffeurEntity);
    }

    @Override
    public List<ChauffeurEntity> findAll() {
        return StreamSupport.stream(chauffeurRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<ChauffeurEntity> findOne(int id) {
        return chauffeurRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return chauffeurRepository.existsById(id);
    }

    @Override
    public ChauffeurEntity partialUpdate(int id, ChauffeurEntity chauffeurEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        chauffeurRepository.deleteById(id);
    }
}
