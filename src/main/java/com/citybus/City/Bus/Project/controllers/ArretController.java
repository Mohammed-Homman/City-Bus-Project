package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ArretDto;
import com.citybus.City.Bus.Project.domain.entities.ArretEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.ArretService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ArretController {
    private ArretService arretService;
    private Mapper<ArretEntity, ArretDto> arretMapper;

    public ArretController(ArretService arretService, Mapper<ArretEntity, ArretDto> arretMapper) {
        this.arretService = arretService;
        this.arretMapper = arretMapper;
    }

    @PostMapping(path = "/Arret")
    public ResponseEntity<ArretDto> createArret(@RequestBody ArretDto arretDto){
        try{
            ArretEntity arretEntity = arretMapper.mapFrom(arretDto);
            ArretEntity savedArretEntity = arretService.save(arretEntity);
            return new ResponseEntity<>(arretMapper.mapTo(savedArretEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path="/Arret")
    public List<ArretDto> listArrets(){
        List<ArretEntity> arretEntities = arretService.findAll();
        return arretEntities.stream().map(arretMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/Arret/{id}")
    public ResponseEntity<ArretDto> getArret(@PathVariable("id") int id){
        Optional<ArretEntity> foundArret = arretService.findOne(id);
        return foundArret.map(arretEntity -> {
            ArretDto arretDto = arretMapper.mapTo(arretEntity);
            return new ResponseEntity<>(arretDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Arret/{id}")
    public ResponseEntity<ArretDto> fullUpdateArret(
            @PathVariable("id") int id,
            @RequestBody ArretDto arretDto){
        if (!arretService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        arretDto.setId(id);
        ArretEntity arretEntity = arretMapper.mapFrom(arretDto);
        ArretEntity savedArretEntity = arretService.save(arretEntity);
        return new ResponseEntity<>(
                arretMapper.mapTo(savedArretEntity),
                HttpStatus.OK);
    }
//    @PatchMapping(path = "/Arret/{id}")
//    public ResponseEntity<ArretDto> partialUpdate(
//            @PathVariable("id") int id,
//            @RequestBody ArretDto arretDto
//    ){
//        if(!arretService.isExists(id)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        ArretEntity arretEntity = arretMapper.mapFrom(arretDto);
//        ArretEntity updatedArret = arretService.partialUpdate(id, arretEntity);
//        return new ResponseEntity<>(
//                arretMapper.mapTo(updatedArret),
//                HttpStatus.OK);
//    }
    @DeleteMapping(path = "/Arret/{id}")
    public ResponseEntity deleteArret(@PathVariable("id") int id){
        arretService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
