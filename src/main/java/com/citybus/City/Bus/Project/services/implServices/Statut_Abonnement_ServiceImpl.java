package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Statut_AbonnementEntity;
import com.citybus.City.Bus.Project.repositories.Statut_Abonnement_Repository;
import com.citybus.City.Bus.Project.services.Statut_Abonnement_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Statut_Abonnement_ServiceImpl implements Statut_Abonnement_Service {
    private Statut_Abonnement_Repository statut_abonnement_repository;


    public Statut_Abonnement_ServiceImpl(Statut_Abonnement_Repository abonnementRepository) {
        this.statut_abonnement_repository = abonnementRepository;
    }

    @Override
    public Statut_AbonnementEntity save(Statut_AbonnementEntity statutAbonnementEntity) {
        return statut_abonnement_repository.save(statutAbonnementEntity);
    }

    @Override
    public List<Statut_AbonnementEntity> findAll() {
        return StreamSupport.stream(statut_abonnement_repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Statut_AbonnementEntity> findOne(int id) {
        return statut_abonnement_repository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return statut_abonnement_repository.existsById(id);
    }

    @Override
    public Statut_AbonnementEntity partialUpdate(int id, Statut_AbonnementEntity statutAbonnementEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        statut_abonnement_repository.deleteById(id);
    }
}
