package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;

import java.util.List;
import java.util.Optional;

public interface AbonnementService {
    AbonnementEntity save(AbonnementEntity abonnementEntity);

    List<AbonnementEntity> findAll();

    boolean isExists(int id);

    Optional<AbonnementEntity> findOne(int id);

    void delete(int id);

    AbonnementEntity partialUpdate(int id, AbonnementEntity abonnementEntity);

    void addClientToAbonnement(int clientId, int abonnementId);


    void updateAbonnementType(int abonnementId, int typeId);

    void removeClientFromAbonnement(int clientId, int abonnementId);

    void updateAbonnementStatut(int abonnementId, int statutId);

    void addLigneToAbonnement(int ligneId, int abonnementId);

    void removeLigneFromAbonnement(int ligneId, int abonnementId);
}
