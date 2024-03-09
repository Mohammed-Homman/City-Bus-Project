package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.LigneDto;
import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.LigneService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LigneController {
    private LigneService ligneService;
    private Mapper<LigneEntity, LigneDto> ligneMapper;

    public LigneController(LigneService ligneService, Mapper<LigneEntity, LigneDto> ligneMapper) {
        this.ligneService = ligneService;
        this.ligneMapper = ligneMapper;
    }

    @PostMapping(path = "/Ligne")
    public ResponseEntity<LigneDto> createLigne(@RequestBody LigneDto ligneDto){
        try{
            LigneEntity ligneEntity = ligneMapper.mapFrom(ligneDto);
            LigneEntity savedLigneEntity = ligneService.save(ligneEntity);
            return new ResponseEntity<>(ligneMapper.mapTo(savedLigneEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path="/Ligne")
    public List<LigneDto> listLignes(){
        List<LigneEntity> ligneEntities = ligneService.findAll();
        return ligneEntities.stream().map(ligneMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Ligne/{id}")
    public ResponseEntity<LigneDto> getLigne(@PathVariable("id") int id){
        Optional<LigneEntity> foundLigne = ligneService.findOne(id);
        return foundLigne.map(ligneEntity -> {
            LigneDto ligneDto = ligneMapper.mapTo(ligneEntity);
            return new ResponseEntity<>(ligneDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Ligne/{id}")
    public ResponseEntity<LigneDto> fullUpdateLigne(
            @PathVariable("id") int id,
            @RequestBody LigneDto ligneDto){
        if (!ligneService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ligneDto.setId(id);
        LigneEntity ligneEntity = ligneMapper.mapFrom(ligneDto);
        LigneEntity savedLigneEntity = ligneService.save(ligneEntity);
        return new ResponseEntity<>(ligneMapper.mapTo(savedLigneEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Ligne/{id}")
    public ResponseEntity<LigneDto> partialUpdateLigne(
            @PathVariable("id") int id,
            @RequestBody LigneDto ligneDto){
        if(!ligneService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LigneEntity ligneEntity = ligneMapper.mapFrom(ligneDto);
        LigneEntity updatedLigne = ligneService.partialUpdate(id, ligneEntity);
        return new ResponseEntity<>(ligneMapper.mapTo(updatedLigne), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Ligne/{id}")
    public ResponseEntity deleteLigne(@PathVariable("id") int id){
        ligneService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/Ligne/{ligneId}/addAbonnement/{abonnementId}")
    public ResponseEntity<Void> addAbonnementToLigne(@PathVariable("ligneId") int ligneId, @PathVariable("abonnementId") int abonnementId) {
        ligneService.addAbonnementToLigne(ligneId, abonnementId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/Ligne/{ligneId}/removeAbonnement/{abonnementId}")
    public ResponseEntity<Void> removeAbonnementFromLigne(@PathVariable("ligneId") int ligneId, @PathVariable("abonnementId") int abonnementId) {
        try {
            ligneService.removeAbonnementFromLigne(ligneId, abonnementId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            // Gérer le cas où la ligne ou l'abonnement n'existe pas
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Gérer toute autre exception inattendue
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping(path = "/Ligne/{ligneId}/addBus/{busId}")
    public ResponseEntity<Void> addBusToLigne(@PathVariable("ligneId") int ligneId, @PathVariable("busId") int busId) {
        ligneService.addBusToLigne(ligneId, busId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/Ligne/{ligneId}/removeBus/{busId}")
    public ResponseEntity<Void> removeBusFromLigne(@PathVariable("ligneId") int ligneId, @PathVariable("busId") int busId) {
        try {
            ligneService.removeBusFromLigne(ligneId, busId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            // Gérer le cas où la ligne ou le bus n'existe pas
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Gérer toute autre exception inattendue
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/Ligne/{ligneId}/addChauffeur/{chauffeurId}")
    public ResponseEntity<Void> addChauffeurToLigne(@PathVariable("ligneId") int ligneId, @PathVariable("chauffeurId") int chauffeurId) {
        ligneService.addChauffeurToLigne(chauffeurId, ligneId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/Ligne/{ligneId}/removeChauffeur/{chauffeurId}")
    public ResponseEntity<Void> removeChauffeurFromLigne(@PathVariable("ligneId") int ligneId,
                                                           @PathVariable("chauffeurId") int chauffeurId) {
        try {
            // Vérifier si la ligne existe
            if (!ligneService.isExists(ligneId)) {
                return ResponseEntity.notFound().build();
            }

            // Supprimer le chauffeur de la ligne
            ligneService.removeChauffeurFromLigne(chauffeurId, ligneId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/Ligne/{ligneId}/addStation/{stationId}")
    public ResponseEntity<Void> addStationToLigne(@PathVariable("ligneId") int ligneId, @PathVariable("stationId") int stationId) {
        ligneService.addStationToLigne(ligneId, stationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/Ligne/{ligneId}/removeStation/{stationId}")
    public ResponseEntity<Void> removeStationFromLigne(@PathVariable("ligneId") int ligneId, @PathVariable("stationId") int stationId) {
        try {
            ligneService.removeStationFromLigne(ligneId, stationId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            // Handle case where the ligne or station does not exist
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(path = "/Ligne/id")
    public ResponseEntity<Integer> getLigneIdByNomLigne(@RequestParam("nomLigne") String nomLigne) {
        Integer ligneId = ligneService.findIdByNomLigne(nomLigne);
        if (ligneId != null) {
            return ResponseEntity.ok().body(ligneId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
