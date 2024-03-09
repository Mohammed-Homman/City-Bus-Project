package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Statut_ChauffeurEntity;
import com.citybus.City.Bus.Project.repositories.Statut_Chauffeur_Repository;
import com.citybus.City.Bus.Project.services.Statut_Chauffeur_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Statut_Chauffeur_ServiceImpl implements Statut_Chauffeur_Service {
    private Statut_Chauffeur_Repository statutChauffeurRepository;

    public Statut_Chauffeur_ServiceImpl(Statut_Chauffeur_Repository statutChauffeurRepository) {
        this.statutChauffeurRepository = statutChauffeurRepository;
    }

    @Override
    public Statut_ChauffeurEntity save(Statut_ChauffeurEntity statutChauffeurEntity) {
        return statutChauffeurRepository.save(statutChauffeurEntity);
    }

    @Override
    public List<Statut_ChauffeurEntity> findAll() {
        return StreamSupport.stream(statutChauffeurRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Statut_ChauffeurEntity> findOne(int id) {
        return statutChauffeurRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return statutChauffeurRepository.existsById(id);
    }

    @Override
    public Statut_ChauffeurEntity partialUpdate(int id, Statut_ChauffeurEntity statutChauffeurEntity) {
        statutChauffeurEntity.setId(id);

        return statutChauffeurRepository.findById(id).map(existingStatut -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(statutChauffeurEntity.getNom_statut_chauffeur()).ifPresent(existingStatut::setNom_statut_chauffeur);

            // Save and return the updated entity
            return statutChauffeurRepository.save(existingStatut);
        }).orElseThrow(() -> new RuntimeException("Statut_ChauffeurEntity does not exist"));
    }


    @Override
    public void delete(int id) {
        statutChauffeurRepository.deleteById(id);
    }
}
