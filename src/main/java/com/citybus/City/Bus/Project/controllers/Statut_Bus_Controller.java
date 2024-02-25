package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Statut_Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.Statut_Bus_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.Statut_Bus_Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Statut_Bus_Controller {
    private Statut_Bus_Service statut_BusService;
    private Mapper<Statut_Bus_Entity, Statut_Bus_Dto> statut_BusMapper;

    public Statut_Bus_Controller(Statut_Bus_Service statut_BusService, Mapper<Statut_Bus_Entity, Statut_Bus_Dto> statut_BusMapper) {
        this.statut_BusService = statut_BusService;
        this.statut_BusMapper = statut_BusMapper;
    }

    @PostMapping(path = "/Statut_Bus")
    public ResponseEntity<Statut_Bus_Dto> createStatut_Bus(@RequestBody Statut_Bus_Dto statut_BusDto) {
        try {
            Statut_Bus_Entity statut_BusEntity = statut_BusMapper.mapFrom(statut_BusDto);
            Statut_Bus_Entity savedStatut_BusEntity = statut_BusService.save(statut_BusEntity);
            return new ResponseEntity<>(statut_BusMapper.mapTo(savedStatut_BusEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path="/Statut_Bus")
    public List<Statut_Bus_Dto> listStatut_Bus(){
        List<Statut_Bus_Entity> statut_BusEntities = statut_BusService.findAll();
        return statut_BusEntities.stream().map(statut_BusMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Statut_Bus/{id}")
    public ResponseEntity<Statut_Bus_Dto> getStatut_Bus(@PathVariable("id") int id){
        Optional<Statut_Bus_Entity> foundStatut_Bus = statut_BusService.findOne(id);
        return foundStatut_Bus.map(statut_BusEntity -> {
            Statut_Bus_Dto statut_BusDto = statut_BusMapper.mapTo(statut_BusEntity);
            return new ResponseEntity<>(statut_BusDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Statut_Bus/{id}")
    public ResponseEntity<Statut_Bus_Dto> fullUpdateStatut_Bus(
            @PathVariable("id") int id,
            @RequestBody Statut_Bus_Dto statut_BusDto){
        if (!statut_BusService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        statut_BusDto.setId(id);
        Statut_Bus_Entity statut_BusEntity = statut_BusMapper.mapFrom(statut_BusDto);
        Statut_Bus_Entity savedStatut_BusEntity = statut_BusService.save(statut_BusEntity);
        return new ResponseEntity<>(statut_BusMapper.mapTo(savedStatut_BusEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Statut_Bus/{id}")
    public ResponseEntity<Statut_Bus_Dto> partialUpdateStatut_Bus(
            @PathVariable("id") int id,
            @RequestBody Statut_Bus_Dto statut_BusDto
    ){
        if(!statut_BusService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Statut_Bus_Entity statut_BusEntity = statut_BusMapper.mapFrom(statut_BusDto);
        Statut_Bus_Entity updatedStatut_Bus = statut_BusService.partialUpdate(id, statut_BusEntity);
        return new ResponseEntity<>(statut_BusMapper.mapTo(updatedStatut_Bus), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Statut_Bus/{id}")
    public ResponseEntity deleteStatut_Bus(@PathVariable("id") int id){
        statut_BusService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
