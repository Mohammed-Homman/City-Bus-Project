package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Statut_AbonnementDto;
import com.citybus.City.Bus.Project.domain.entities.Statut_AbonnementEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.Statut_Abonnement_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Statut_AbonnementController {
    private Statut_Abonnement_Service statut_AbonnementService;
    private Mapper<Statut_AbonnementEntity, Statut_AbonnementDto> statut_AbonnementMapper;

    public Statut_AbonnementController(Statut_Abonnement_Service statut_AbonnementService, Mapper<Statut_AbonnementEntity, Statut_AbonnementDto> statut_AbonnementMapper) {
        this.statut_AbonnementService = statut_AbonnementService;
        this.statut_AbonnementMapper = statut_AbonnementMapper;
    }

    @PostMapping(path = "/Statut_Abonnement")
    public ResponseEntity<Statut_AbonnementDto> createStatut_Abonnement(@RequestBody Statut_AbonnementDto statut_AbonnementDto) {
        Statut_AbonnementEntity statut_AbonnementEntity = statut_AbonnementMapper.mapFrom(statut_AbonnementDto);
        Statut_AbonnementEntity savedStatut_AbonnementEntity = statut_AbonnementService.save(statut_AbonnementEntity);
        return new ResponseEntity<>(statut_AbonnementMapper.mapTo(savedStatut_AbonnementEntity), HttpStatus.CREATED);
    }

    @GetMapping(path="/Statut_Abonnement")
    public List<Statut_AbonnementDto> listStatut_Abonnements(){
        List<Statut_AbonnementEntity> statut_AbonnementEntities = statut_AbonnementService.findAll();
        return statut_AbonnementEntities.stream().map(statut_AbonnementMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Statut_Abonnement/{id}")
    public ResponseEntity<Statut_AbonnementDto> getStatut_Abonnement(@PathVariable("id") int id){
        Optional<Statut_AbonnementEntity> foundStatut_Abonnement = statut_AbonnementService.findOne(id);
        return foundStatut_Abonnement.map(statut_AbonnementEntity -> {
            Statut_AbonnementDto statut_AbonnementDto = statut_AbonnementMapper.mapTo(statut_AbonnementEntity);
            return new ResponseEntity<>(statut_AbonnementDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Statut_Abonnement/{id}")
    public ResponseEntity<Statut_AbonnementDto> fullUpdateStatut_Abonnement(
            @PathVariable("id") int id,
            @RequestBody Statut_AbonnementDto statut_AbonnementDto){
        if (!statut_AbonnementService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        statut_AbonnementDto.setId(id);
        Statut_AbonnementEntity statut_AbonnementEntity = statut_AbonnementMapper.mapFrom(statut_AbonnementDto);
        Statut_AbonnementEntity savedStatut_AbonnementEntity = statut_AbonnementService.save(statut_AbonnementEntity);
        return new ResponseEntity<>(statut_AbonnementMapper.mapTo(savedStatut_AbonnementEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Statut_Abonnement/{id}")
    public ResponseEntity<Statut_AbonnementDto> partialUpdateStatut_Abonnement(
            @PathVariable("id") int id,
            @RequestBody Statut_AbonnementDto statut_AbonnementDto
    ){
        if(!statut_AbonnementService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Statut_AbonnementEntity statut_AbonnementEntity = statut_AbonnementMapper.mapFrom(statut_AbonnementDto);
        Statut_AbonnementEntity updatedStatut_Abonnement = statut_AbonnementService.partialUpdate(id, statut_AbonnementEntity);
        return new ResponseEntity<>(statut_AbonnementMapper.mapTo(updatedStatut_Abonnement), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Statut_Abonnement/{id}")
    public ResponseEntity deleteStatut_Abonnement(@PathVariable("id") int id){
        statut_AbonnementService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

