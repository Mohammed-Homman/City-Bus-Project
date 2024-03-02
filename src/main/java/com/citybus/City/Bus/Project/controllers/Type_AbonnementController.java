package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.dto.Type_AbonnementDto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.Type_Abonnement_Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Type_AbonnementController {
    private Type_Abonnement_Service type_abonnement_service;
    private Mapper<Type_AbonnementEntity, Type_AbonnementDto> typeAbonnementMapper;

    public Type_AbonnementController(Type_Abonnement_Service type_abonnement_service, Mapper<Type_AbonnementEntity, Type_AbonnementDto> typeAbonnementMapper) {
        this.type_abonnement_service = type_abonnement_service;
        this.typeAbonnementMapper = typeAbonnementMapper;
    }

    @PostMapping(path = "/Type_Abonnement")
    public ResponseEntity<Type_AbonnementDto> createTypeAbonnement(@RequestBody Type_AbonnementDto typeAbonnementDto) {
        try {
            Type_AbonnementEntity typeAbonnementEntity = typeAbonnementMapper.mapFrom(typeAbonnementDto);
            Type_AbonnementEntity savedTypeAbonnementEntity = type_abonnement_service.save(typeAbonnementEntity);
            return new ResponseEntity<>(typeAbonnementMapper.mapTo(savedTypeAbonnementEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/Type_Abonnement")
    public List<Type_AbonnementDto> listTypeAbonnements() {
        List<Type_AbonnementEntity> typeAbonnementEntities = type_abonnement_service.findAll();
        return typeAbonnementEntities.stream().map(typeAbonnementMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Type_Abonnement/{id}")
    public ResponseEntity<Type_AbonnementDto> getTypeAbonnement(@PathVariable("id") int id) {
        Optional<Type_AbonnementEntity> foundTypeAbonnement = type_abonnement_service.findOne(id);
        return foundTypeAbonnement.map(typeAbonnementEntity -> new ResponseEntity<>(typeAbonnementMapper.mapTo(typeAbonnementEntity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Type_Abonnement/{id}")
    public ResponseEntity<Type_AbonnementDto> fullTypeAbonnement(@PathVariable("id") int id, @RequestBody Type_AbonnementDto typeAbonnementDto) {
        if (!type_abonnement_service.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeAbonnementDto.setId(id);
        Type_AbonnementEntity typeAbonnementEntity = typeAbonnementMapper.mapFrom(typeAbonnementDto);
        Type_AbonnementEntity savedTypeAbonnementEntity = type_abonnement_service.save(typeAbonnementEntity);
        return new ResponseEntity<>(typeAbonnementMapper.mapTo(savedTypeAbonnementEntity), HttpStatus.OK);
    }
    @PatchMapping(path = "/Type_Abonnement/{id}")
    public ResponseEntity<Type_AbonnementDto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody Type_AbonnementDto typeAbonnementDto){
        if (!type_abonnement_service.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type_AbonnementEntity typeAbonnementEntity = typeAbonnementMapper.mapFrom(typeAbonnementDto);
        Type_AbonnementEntity savedTypeAbonnementEntity = type_abonnement_service.partialUpdate(id,typeAbonnementEntity);
        return new ResponseEntity<>(
                typeAbonnementMapper.mapTo(savedTypeAbonnementEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/Type_Abonnement/{id}")
    public ResponseEntity deleteTypeAbonnement(@PathVariable("id") int id) {
        type_abonnement_service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
