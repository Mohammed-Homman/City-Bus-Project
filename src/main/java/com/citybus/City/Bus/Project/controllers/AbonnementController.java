package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.AbonnementDto;
import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.mappers.implementations.AbonnementMapper;
import com.citybus.City.Bus.Project.services.AbonnementService;
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
  public ResponseEntity<AbonnementDto> createAbonnement(@RequestBody AbonnementDto abonnementDto){
    AbonnementEntity abonnementEntity = abonnementMapper.mapFrom(abonnementDto);
    AbonnementEntity savedAbonnementEntity = abonnementService.save(abonnementEntity);
    return new ResponseEntity<>(abonnementMapper.mapTo(savedAbonnementEntity), HttpStatus.CREATED);
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
//  @PatchMapping(path = "/Abonnement/{id}")
//  public ResponseEntity<AbonnementDto> partialUpdate(
//          @PathVariable("id") int id,
//          @RequestBody AbonnementDto abonnementDto
//  ){
//    if(!abonnementService.isExists(id)){
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    AbonnementEntity abonnementEntity = abonnementMapper.mapFrom(abonnementDto);
//    AbonnementEntity updatedAbonnement = abonnementService.partialUpdate(id, abonnementEntity);
//    return new ResponseEntity<>(
//            abonnementMapper.mapTo(updatedAbonnement),
//            HttpStatus.OK);
//  }

  @DeleteMapping(path = "/Abonnement/{id}")
  public ResponseEntity deleteAbonnement(@PathVariable("id") int id){
    abonnementService.delete(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
