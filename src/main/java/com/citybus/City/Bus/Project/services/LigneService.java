package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;

import java.util.List;
import java.util.Optional;

public interface LigneService {
    LigneEntity save(LigneEntity ligneEntity);

    List<LigneEntity> findAll();

    Optional<LigneEntity> findOne(int id);

    boolean isExists(int id);

    LigneEntity partialUpdate(int id, LigneEntity ligneEntity);

    void delete(int id);

    void addAbonnementToLigne(int ligneId, int abonnementId);

    void removeAbonnementFromLigne(int ligneId, int abonnementId);

    void addBusToLigne(int ligneId, int busId);

    void removeBusFromLigne(int ligneId, int busId);

    void addChauffeurToLigne(int chauffeurId, int ligneId);

    void removeChauffeurFromLigne(int chauffeurId, int ligneId);

    void addStationToLigne(int ligneId, int stationId);

    void removeStationFromLigne(int ligneId, int stationId);

    Integer findIdByNomLigne(String nomLigne);
}
