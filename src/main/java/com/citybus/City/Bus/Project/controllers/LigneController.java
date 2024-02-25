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
}
