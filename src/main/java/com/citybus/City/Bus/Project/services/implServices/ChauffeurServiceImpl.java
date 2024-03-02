package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;
import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import com.citybus.City.Bus.Project.domain.entities.Statut_ChauffeurEntity;
import com.citybus.City.Bus.Project.repositories.ChauffeurRepository;
import com.citybus.City.Bus.Project.repositories.LigneRepository;
import com.citybus.City.Bus.Project.repositories.Statut_Chauffeur_Repository;
import com.citybus.City.Bus.Project.services.ChauffeurService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChauffeurServiceImpl implements ChauffeurService {
    private ChauffeurRepository chauffeurRepository;
    private Statut_Chauffeur_Repository statutChauffeurRepository;
    private LigneRepository ligneRepository;

    public ChauffeurServiceImpl(ChauffeurRepository chauffeurRepository, Statut_Chauffeur_Repository statutChauffeurRepository, LigneRepository ligneRepository) {
        this.chauffeurRepository = chauffeurRepository;
        this.statutChauffeurRepository = statutChauffeurRepository;
        this.ligneRepository = ligneRepository;
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
        chauffeurEntity.setId(id);

        return chauffeurRepository.findById(id).map(existingChauffeur -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(chauffeurEntity.getNom_chauffeur()).ifPresent(existingChauffeur::setNom_chauffeur);
            Optional.ofNullable(chauffeurEntity.getPrenom_chauffeur()).ifPresent(existingChauffeur::setPrenom_chauffeur);
            Optional.ofNullable(chauffeurEntity.getDate_naissance()).ifPresent(existingChauffeur::setDate_naissance);
            Optional.ofNullable(chauffeurEntity.getAdresse()).ifPresent(existingChauffeur::setAdresse);
            Optional.ofNullable(chauffeurEntity.getTelephone()).ifPresent(existingChauffeur::setTelephone);
            Optional.ofNullable(chauffeurEntity.getEmail()).ifPresent(existingChauffeur::setEmail);
            Optional.ofNullable(chauffeurEntity.getDate_recrutement()).ifPresent(existingChauffeur::setDate_recrutement);
            Optional.ofNullable(chauffeurEntity.getSalaire()).ifPresent(existingChauffeur::setSalaire);
            Optional.ofNullable(chauffeurEntity.getStatutChauffeurEntity()).ifPresent(existingChauffeur::setStatutChauffeurEntity);
            Optional.ofNullable(chauffeurEntity.getLignes()).ifPresent(existingChauffeur::setLignes);

            // Save and return the updated entity
            return chauffeurRepository.save(existingChauffeur);
        }).orElseThrow(() -> new RuntimeException("Chauffeur does not exist"));
    }


    @Override
    public void delete(int id) {
        chauffeurRepository.deleteById(id);
    }

    @Override
    public void updateBusStatut(int chauffeurId, int statutId) {
        ChauffeurEntity chauffeurEntity = chauffeurRepository.findById(chauffeurId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chauffeur not found"));
        Statut_ChauffeurEntity newStatut = statutChauffeurRepository.findById(statutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Statut_Chauffeur not found"));
        chauffeurEntity.setStatutChauffeurEntity(newStatut);
        chauffeurRepository.save(chauffeurEntity);
    }

    @Override
    public void addLigneToChauffeur(int ligneId, int chauffeurId) {
        ChauffeurEntity chauffeur = chauffeurRepository.findById(chauffeurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chauffeur not found"));
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));

        ligne.getChauffeurs().add(chauffeur);
        chauffeur.getLignes().add(ligne);

        ligneRepository.save(ligne);
        chauffeurRepository.save(chauffeur);
    }

    @Override
    public void removeLigneFromChauffeur(int ligneId, int chauffeurId) {
        ChauffeurEntity chauffeur = chauffeurRepository.findById(chauffeurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chauffeur not found"));
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));

        ligne.getChauffeurs().remove(chauffeur);
        chauffeur.getLignes().remove(ligne);

        ligneRepository.save(ligne);
        chauffeurRepository.save(chauffeur);
    }
}
