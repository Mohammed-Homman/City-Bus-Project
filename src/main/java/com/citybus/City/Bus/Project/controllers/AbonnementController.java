package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.AbonnementDto;
import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.AbonnementService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AbonnementController {
  private AbonnementService abonnementService;
  private Mapper<AbonnementEntity, AbonnementDto> abonnementMapper;

  public AbonnementController(AbonnementService abonnementService, Mapper<AbonnementEntity, AbonnementDto> abonnementMapper) {
    this.abonnementService = abonnementService;
    this.abonnementMapper = abonnementMapper;
  }

  @PostMapping(path = "/Abonnement")
  public ResponseEntity<AbonnementDto> createAbonnement(@RequestBody AbonnementDto abonnementDto) {
    try {
      AbonnementEntity abonnementEntity = abonnementMapper.mapFrom(abonnementDto);
      AbonnementEntity savedAbonnementEntity = abonnementService.save(abonnementEntity);
      return new ResponseEntity<>(abonnementMapper.mapTo(savedAbonnementEntity), HttpStatus.CREATED);
    } catch (DataIntegrityViolationException e) {
      // Handle duplicate key violation gracefully
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @GetMapping(path = "/Abonnement")
  public List<AbonnementDto> listAbonnements(){
    List<AbonnementEntity> abonnementEntities = abonnementService.findAll();
    return abonnementEntities.stream().map(abonnementMapper::mapTo).collect(Collectors.toList());
  }
  @GetMapping(path = "/Abonnement/{id}")
  public ResponseEntity<AbonnementDto> getAbonnement(@PathVariable("id") int id){
    Optional<AbonnementEntity> foundAbonnement = abonnementService.findOne(id);
    return foundAbonnement.map(abonnementEntity -> {
      AbonnementDto abonnementDto = abonnementMapper.mapTo(abonnementEntity);
      return new ResponseEntity<>(abonnementDto,HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping(path = "/Abonnement/{id}")
  public ResponseEntity<AbonnementDto> fullUpdateAbonnement(
          @PathVariable("id") int id,
          @RequestBody AbonnementDto abonnementDto){
    if (!abonnementService.isExists(id)) {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    abonnementDto.setId(id);
    AbonnementEntity abonnementEntity = abonnementMapper.mapFrom(abonnementDto);
    AbonnementEntity savedAbonnementEntity = abonnementService.save(abonnementEntity);
    return new ResponseEntity<>(
            abonnementMapper.mapTo(savedAbonnementEntity),
            HttpStatus.OK);
  }
  @PatchMapping(path = "/Abonnement/{id}")
  public ResponseEntity<AbonnementDto> partialUpdate(
          @PathVariable("id") int id,
          @RequestBody AbonnementDto abonnementDto
  ){
    if(!abonnementService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    AbonnementEntity abonnementEntity = abonnementMapper.mapFrom(abonnementDto);
    AbonnementEntity updatedAbonnement = abonnementService.partialUpdate(id, abonnementEntity);
    return new ResponseEntity<>(
            abonnementMapper.mapTo(updatedAbonnement),
            HttpStatus.OK);
  }

  @DeleteMapping(path = "/Abonnement/{id}")
  public ResponseEntity deleteAbonnement(@PathVariable("id") int id){
    abonnementService.delete(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
  @PutMapping(path = "/Abonnement/{abonnementId}/addClient/{clientId}")
  public ResponseEntity<Void> addClientToAbonnement(@PathVariable("abonnementId") int abonnementId, @PathVariable("clientId") int clientId) {
    abonnementService.addClientToAbonnement(clientId, abonnementId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @DeleteMapping("/Abonnement/{abonnementId}/removeClient/{clientId}")
  public ResponseEntity<Void> removeClientFromAbonnement(@PathVariable("abonnementId") int abonnementId,
                                                         @PathVariable("clientId") int clientId) {
    try {
      // Vérifier si l'abonnement existe
      if (!abonnementService.isExists(abonnementId)) {
        return ResponseEntity.notFound().build();
      }

      // Supprimer le client de l'abonnement
      abonnementService.removeClientFromAbonnement(clientId, abonnementId);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/Abonnement/{abonnementId}/updateType/{typeId}")
  public ResponseEntity<?> updateAbonnementType(@PathVariable("abonnementId") int abonnementId,
                                                @PathVariable("clientId") int typeId) {
    try {
      abonnementService.updateAbonnementType(abonnementId, typeId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update subscription type.");
    }
  }

  @PostMapping("/Abonnement/{abonnementId}/updateStatut/{statutId}")
  public ResponseEntity<?> updateAbonnementStatut(@PathVariable("abonnementId") int abonnementId,
                                                  @PathVariable("statutId") int statutId) {
    try {
      abonnementService.updateAbonnementStatut(abonnementId, statutId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update subscription status.");
    }
  }

  @PutMapping(path = "/Abonnement/{abonnementId}/addLigne/{ligneId}")
  public ResponseEntity<Void> addLigneToAbonnement(@PathVariable("abonnementId") int abonnementId, @PathVariable("ligneId") int ligneId) {
    try {
      // Check if the abonnement exists
      if (!abonnementService.isExists(abonnementId)) {
        return ResponseEntity.notFound().build();
      }

      // Add the ligne to the abonnement
      abonnementService.addLigneToAbonnement(ligneId, abonnementId);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/Abonnement/{abonnementId}/removeLigne/{ligneId}")
  public ResponseEntity<Void> removeLigneFromAbonnement(@PathVariable("abonnementId") int abonnementId,
                                                        @PathVariable("ligneId") int ligneId) {
    try {
      // Check if the abonnement exists
      if (!abonnementService.isExists(abonnementId)) {
        return ResponseEntity.notFound().build();
      }

      // Remove the ligne from the abonnement
      abonnementService.removeLigneFromAbonnement(ligneId, abonnementId);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
