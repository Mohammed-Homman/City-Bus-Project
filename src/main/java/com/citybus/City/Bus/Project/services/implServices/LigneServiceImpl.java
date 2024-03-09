package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.*;
import com.citybus.City.Bus.Project.repositories.*;
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
    private BusRepository busRepository;
    private ChauffeurRepository chauffeurRepository;
    private StationRepository stationRepository;

    public LigneServiceImpl(LigneRepository ligneRepository, AbonnementRepository abonnementRepository, BusRepository busRepository, ChauffeurRepository chauffeurRepository, StationRepository stationRepository) {
        this.ligneRepository = ligneRepository;
        this.abonnementRepository = abonnementRepository;
        this.busRepository = busRepository;
        this.chauffeurRepository = chauffeurRepository;
        this.stationRepository = stationRepository;
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
        ligneEntity.setId(id);

        return ligneRepository.findById(id).map(existingLigne -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(ligneEntity.getNom_ligne()).ifPresent(existingLigne::setNom_ligne);
            Optional.ofNullable(ligneEntity.getDescription_ligne()).ifPresent(existingLigne::setDescription_ligne);
            Optional.ofNullable(ligneEntity.getDistance_ligne()).ifPresent(existingLigne::setDistance_ligne);
            Optional.ofNullable(ligneEntity.getAbonnements()).ifPresent(existingLigne::setAbonnements);
            Optional.ofNullable(ligneEntity.getBuss()).ifPresent(existingLigne::setBuss);
            Optional.ofNullable(ligneEntity.getChauffeurs()).ifPresent(existingLigne::setChauffeurs);
            Optional.ofNullable(ligneEntity.getStations()).ifPresent(existingLigne::setStations);

            // Save and return the updated entity
            return ligneRepository.save(existingLigne);
        }).orElseThrow(() -> new RuntimeException("Ligne does not exist"));
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

    @Override
    public void addBusToLigne(int ligneId, int busId) {
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));
        Bus_Entity bus = busRepository.findById(busId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found"));

        ligne.getBuss().add(bus);
        bus.setLigne(ligne);

        ligneRepository.save(ligne);
        busRepository.save(bus);
    }

    @Override
    public void removeBusFromLigne(int ligneId, int busId) {
        LigneEntity ligne = ligneRepository.findById(ligneId)
                .orElseThrow(NoSuchElementException::new);

        Bus_Entity busToRemove = null;
        for (Bus_Entity bus : ligne.getBuss()) {
            if (bus.getId() == busId) {
                busToRemove = bus;
                break;
            }
        }

        if (busToRemove != null) {
            ligne.getBuss().remove(busToRemove);
            ligneRepository.save(ligne);
        } else {
            throw new NoSuchElementException("Bus not found for the ligne");
        }
    }

    @Override
    public void addChauffeurToLigne(int chauffeurId, int ligneId) {
        ChauffeurEntity chauffeur = chauffeurRepository.findById(chauffeurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chauffeur not found"));
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));

        ligne.getChauffeurs().add(chauffeur);
        chauffeur.getLignes().add(ligne);

        ligneRepository.save(ligne);
        chauffeurRepository.save(chauffeur);
    }

    @Override
    public void removeChauffeurFromLigne(int chauffeurId, int ligneId) {
        ChauffeurEntity chauffeur = chauffeurRepository.findById(chauffeurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chauffeur not found"));
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));

        ligne.getChauffeurs().remove(chauffeur);
        chauffeur.getLignes().remove(ligne);

        ligneRepository.save(ligne);
        chauffeurRepository.save(chauffeur);
    }

    @Override
    public void addStationToLigne(int stationId, int ligneId) {
        Station_Entity station = stationRepository.findById(stationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found"));
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));

        ligne.getStations().add(station);
        station.setLigne(ligne);

        ligneRepository.save(ligne);
        stationRepository.save(station);
    }

    @Override
    public void removeStationFromLigne(int stationId, int ligneId) {
        Station_Entity station = stationRepository.findById(stationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found"));
        LigneEntity ligne = ligneRepository.findById(ligneId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne not found"));

        ligne.getStations().remove(station);
        station.setLigne(null);

        ligneRepository.save(ligne);
        stationRepository.save(station);
    }

    @Override
    public Integer findIdByNomLigne(String nomLigne) {
        return ligneRepository.findIdByNomLigne(nomLigne);
    }
}
