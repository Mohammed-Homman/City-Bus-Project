package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.*;
import com.citybus.City.Bus.Project.repositories.*;
import com.citybus.City.Bus.Project.services.AbonnementService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AbonnementServiceImpl implements AbonnementService {
    private AbonnementRepository abonnementRepository;
    private ClientRepository clientRepository;
    private Type_Abonnement_Repository type_abonnement_repository;
    private Statut_Abonnement_Repository statut_abonnement_repository;
    private LigneRepository ligneRepository;

    public AbonnementServiceImpl(AbonnementRepository abonnementRepository, ClientRepository clientRepository, Type_Abonnement_Repository type_abonnement_repository, Statut_Abonnement_Repository statut_abonnement_repository, LigneRepository ligneRepository) {
        this.abonnementRepository = abonnementRepository;
        this.clientRepository = clientRepository;
        this.type_abonnement_repository = type_abonnement_repository;
        this.statut_abonnement_repository = statut_abonnement_repository;
        this.ligneRepository = ligneRepository;
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

    @Override
    public AbonnementEntity partialUpdate(int id, AbonnementEntity abonnementEntity) {
        abonnementEntity.setId(id);

        return abonnementRepository.findById(id).map(existingAbonnement -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(abonnementEntity.getDate_debut()).ifPresent(existingAbonnement::setDate_debut);
            Optional.ofNullable(abonnementEntity.getDate_fin()).ifPresent(existingAbonnement::setDate_fin);
            Optional.ofNullable(abonnementEntity.getPrix()).ifPresent(existingAbonnement::setPrix);
            Optional.ofNullable(abonnementEntity.getTypeAbonnementEntity()).ifPresent(existingAbonnement::setTypeAbonnementEntity);
            Optional.ofNullable(abonnementEntity.getStatutAbonnementEntity()).ifPresent(existingAbonnement::setStatutAbonnementEntity);
            Optional.ofNullable(abonnementEntity.getLignes()).ifPresent(existingAbonnement::setLignes);

            // Save and return the updated entity
            return abonnementRepository.save(existingAbonnement);
        }).orElseThrow(() -> new RuntimeException("Abonnement does not exist"));
    }


    @Override
    public void addClientToAbonnement(int clientId, int abonnementId) {
        ClientEntity client = clientRepository.findById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        abonnement.getClients().add(client);
        client.getAbonnements().add(abonnement);

        abonnementRepository.save(abonnement);
        clientRepository.save(client);
    }

    @Override
    public void updateAbonnementType(int abonnementId, int typeId) {
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        Type_AbonnementEntity newType = type_abonnement_repository.findById(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type_Abonnement not found"));

        abonnement.setTypeAbonnementEntity(newType);
        abonnementRepository.save(abonnement);
    }

    @Override
    public void removeClientFromAbonnement(int clientId, int abonnementId) {
        ClientEntity client = clientRepository.findById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        abonnement.getClients().remove(client);
        client.getAbonnements().remove(abonnement);

        abonnementRepository.save(abonnement);
        clientRepository.save(client);
    }

    @Override
    public void updateAbonnementStatut(int abonnementId, int statutId) {
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        Statut_AbonnementEntity newStatut = statut_abonnement_repository.findById(statutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Statut_Abonnement not found"));

        abonnement.setStatutAbonnementEntity(newStatut);
        abonnementRepository.save(abonnement);
    }

    @Override
    public void addLigneToAbonnement(int ligneId, int abonnementId) {
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        abonnement.getLignes().add(ligne);
        ligne.getAbonnements().add(abonnement);

        abonnementRepository.save(abonnement);
        ligneRepository.save(ligne);
    }

    @Override
    public void removeLigneFromAbonnement(int ligneId, int abonnementId) {
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        abonnement.getLignes().remove(ligne);
        ligne.getAbonnements().remove(abonnement);

        abonnementRepository.save(abonnement);
        ligneRepository.save(ligne);
    }

}
