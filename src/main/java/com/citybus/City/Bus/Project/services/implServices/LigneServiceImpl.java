package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import com.citybus.City.Bus.Project.repositories.AbonnementRepository;
import com.citybus.City.Bus.Project.repositories.LigneRepository;
import com.citybus.City.Bus.Project.services.LigneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LigneServiceImpl implements LigneService {
    private LigneRepository ligneRepository;
    private AbonnementRepository abonnementRepository;

    public LigneServiceImpl(LigneRepository ligneRepository, AbonnementRepository abonnementRepository) {
        this.ligneRepository = ligneRepository;
        this.abonnementRepository = abonnementRepository;
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

    @Override
    public void addAbonnementToLigne(int ligneId, int abonnementId) {
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        ligne.getAbonnements().add(abonnement);
        abonnement.getLignes().add(ligne);

        ligneRepository.save(ligne);
        abonnementRepository.save(abonnement);
    }

    @Override
    public void removeAbonnementFromLigne(int ligneId, int abonnementId) {
        LigneEntity ligne = ligneRepository.findById(ligneId)
                .orElseThrow(NoSuchElementException::new);

        AbonnementEntity abonnementToRemove = null;
        for (AbonnementEntity abonnement : ligne.getAbonnements()) {
            if (abonnement.getId() == abonnementId) {
                abonnementToRemove = abonnement;
                break;
            }
        }

        if (abonnementToRemove != null) {
            ligne.getAbonnements().remove(abonnementToRemove);
            ligneRepository.save(ligne);
        } else {
            throw new NoSuchElementException("Abonnement not found for the ligne");
        }
    }

}
