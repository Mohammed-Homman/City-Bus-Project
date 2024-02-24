package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.repositories.AbonnementRepository;
import com.citybus.City.Bus.Project.services.AbonnementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AbonnementServiceImpl implements AbonnementService {
    private AbonnementRepository abonnementRepository;

    public AbonnementServiceImpl(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    @Override
    public AbonnementEntity save(AbonnementEntity abonnementEntity) {
        return abonnementRepository.save(abonnementEntity);
    }

    @Override
    public List<AbonnementEntity> findAll() {
        return StreamSupport.stream(abonnementRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public boolean isExists(int id) {
        return abonnementRepository.existsById(id);
    }

    @Override
    public Optional<AbonnementEntity> findOne(int id) {
        return abonnementRepository.findById(id);
    }
    @Override
    public void delete(int id) {
        abonnementRepository.deleteById(id);
    }
}
