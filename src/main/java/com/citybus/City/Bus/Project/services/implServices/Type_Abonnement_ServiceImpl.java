package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;
import com.citybus.City.Bus.Project.repositories.Type_Abonnement_Repository;
import com.citybus.City.Bus.Project.services.Type_Abonnement_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Type_Abonnement_ServiceImpl implements Type_Abonnement_Service {
    private Type_Abonnement_Repository type_abonnement_repository;

    public Type_Abonnement_ServiceImpl(Type_Abonnement_Repository type_abonnement_repository) {
        this.type_abonnement_repository = type_abonnement_repository;
    }

    @Override
    public Type_AbonnementEntity save(Type_AbonnementEntity typeAbonnementEntity) {
        return type_abonnement_repository.save(typeAbonnementEntity);
    }

    @Override
    public List<Type_AbonnementEntity> findAll() {
        return StreamSupport.stream(type_abonnement_repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Type_AbonnementEntity> findOne(int id) {
        return type_abonnement_repository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return type_abonnement_repository.existsById(id);
    }

    @Override
    public Type_AbonnementEntity partialUpdate(int id, Type_AbonnementEntity typeAbonnementEntity) {
        typeAbonnementEntity.setId(id);

        return type_abonnement_repository.findById(id).map(existingType -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(typeAbonnementEntity.getNom_type_abonnement()).ifPresent(existingType::setNom_type_abonnement);

            // Save and return the updated entity
            return type_abonnement_repository.save(existingType);
        }).orElseThrow(() -> new RuntimeException("Type_AbonnementEntity does not exist"));
    }


    @Override
    public void delete(int id) {
        type_abonnement_repository.deleteById(id);
    }

}
