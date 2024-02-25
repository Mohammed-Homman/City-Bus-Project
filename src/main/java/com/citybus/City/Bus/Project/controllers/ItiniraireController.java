package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.dto.Itiniraire_Dto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.domain.entities.Itiniraire_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.ClientService;
import com.citybus.City.Bus.Project.services.ItiniraireService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
public class ItiniraireController {
    private ItiniraireService itiniraireService;
    private Mapper<Itiniraire_Entity, Itiniraire_Dto> itiniraireMapper;

    public ItiniraireController(ItiniraireService itiniraireService, Mapper<Itiniraire_Entity, Itiniraire_Dto> itiniraireMapper) {
        this.itiniraireService = itiniraireService;
        this.itiniraireMapper = itiniraireMapper;
    }

    @PostMapping(path = "/Itiniraire")
    public ResponseEntity<Itiniraire_Dto> createItiniraire(@RequestBody Itiniraire_Dto itiniraireDto){
        try{
            Itiniraire_Entity itiniraireEntity = itiniraireMapper.mapFrom(itiniraireDto);
            Itiniraire_Entity savedItiniraireEntity = itiniraireService.save(itiniraireEntity);
            return new ResponseEntity<>(itiniraireMapper.mapTo(savedItiniraireEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path="/Itiniraire")
    public List<Itiniraire_Dto> listItiniraires(){
        List<Itiniraire_Entity> itiniraireEntities = itiniraireService.findAll();
        return itiniraireEntities.stream().map(itiniraireMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/Itiniraire/{id}")
    public ResponseEntity<Itiniraire_Dto> getItiniraire(@PathVariable("id") int id){
        Optional<Itiniraire_Entity> foundItiniraire = itiniraireService.findOne(id);
        return foundItiniraire.map(itiniraireEntity -> {
            Itiniraire_Dto itiniraireDto = itiniraireMapper.mapTo(itiniraireEntity);
            return new ResponseEntity<>(itiniraireDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Itiniraire/{id}")
    public ResponseEntity<Itiniraire_Dto> fullUpdateItiniraire(
            @PathVariable("id") int id,
            @RequestBody Itiniraire_Dto itiniraireDto){
        if (!itiniraireService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itiniraireDto.setId(id);
        Itiniraire_Entity itiniraireEntity = itiniraireMapper.mapFrom(itiniraireDto);
        Itiniraire_Entity savedItiniraireEntity = itiniraireService.save(itiniraireEntity);
        return new ResponseEntity<>(
                itiniraireMapper.mapTo(savedItiniraireEntity),
                HttpStatus.OK);
    }
    @PatchMapping(path = "/Itiniraire/{id}")
    public ResponseEntity<Itiniraire_Dto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody Itiniraire_Dto itiniraireDto
    ){
        if(!itiniraireService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Itiniraire_Entity itiniraireEntity = itiniraireMapper.mapFrom(itiniraireDto);
        Itiniraire_Entity updatedItiniraire = itiniraireService.partialUpdate(id, itiniraireEntity);
        return new ResponseEntity<>(
                itiniraireMapper.mapTo(updatedItiniraire),
                HttpStatus.OK);
    }
    @DeleteMapping(path = "/Itiniraire/{id}")
    public ResponseEntity deleteItiniraire(@PathVariable("id") int id){
        itiniraireService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
