package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Statut_ChauffeurDto;
import com.citybus.City.Bus.Project.domain.entities.Statut_ChauffeurEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.Statut_Chauffeur_Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Statut_ChauffeurController {
    private Statut_Chauffeur_Service statutChauffeurService;
    private Mapper<Statut_ChauffeurEntity, Statut_ChauffeurDto> statutChauffeurMapper;

    public Statut_ChauffeurController(Statut_Chauffeur_Service statutChauffeurService, Mapper<Statut_ChauffeurEntity, Statut_ChauffeurDto> statutChauffeurMapper) {
        this.statutChauffeurService = statutChauffeurService;
        this.statutChauffeurMapper = statutChauffeurMapper;
    }

    @PostMapping(path = "/Statut_Chauffeur")
    public ResponseEntity<Statut_ChauffeurDto> createStatutChauffeur(@RequestBody Statut_ChauffeurDto statutChauffeurDto) {
        try {
            Statut_ChauffeurEntity statutChauffeurEntity = statutChauffeurMapper.mapFrom(statutChauffeurDto);
            Statut_ChauffeurEntity savedStatutChauffeurEntity = statutChauffeurService.save(statutChauffeurEntity);
            return new ResponseEntity<>(statutChauffeurMapper.mapTo(savedStatutChauffeurEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path="/Statut_Chauffeur")
    public List<Statut_ChauffeurDto> listStatutChauffeurs(){
        List<Statut_ChauffeurEntity> statutChauffeurEntities = statutChauffeurService.findAll();
        return statutChauffeurEntities.stream().map(statutChauffeurMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Statut_Chauffeur/{id}")
    public ResponseEntity<Statut_ChauffeurDto> getStatutChauffeur(@PathVariable("id") int id){
        Optional<Statut_ChauffeurEntity> foundStatutChauffeur = statutChauffeurService.findOne(id);
        return foundStatutChauffeur.map(statutChauffeurEntity -> {
            Statut_ChauffeurDto statutChauffeurDto = statutChauffeurMapper.mapTo(statutChauffeurEntity);
            return new ResponseEntity<>(statutChauffeurDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Statut_Chauffeur/{id}")
    public ResponseEntity<Statut_ChauffeurDto> fullUpdateStatutChauffeur(
            @PathVariable("id") int id,
            @RequestBody Statut_ChauffeurDto statutChauffeurDto){
        if (!statutChauffeurService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        statutChauffeurDto.setId(id);
        Statut_ChauffeurEntity statutChauffeurEntity = statutChauffeurMapper.mapFrom(statutChauffeurDto);
        Statut_ChauffeurEntity savedStatutChauffeurEntity = statutChauffeurService.save(statutChauffeurEntity);
        return new ResponseEntity<>(statutChauffeurMapper.mapTo(savedStatutChauffeurEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Statut_Chauffeur/{id}")
    public ResponseEntity<Statut_ChauffeurDto> partialUpdateStatutChauffeur(
            @PathVariable("id") int id,
            @RequestBody Statut_ChauffeurDto statutChauffeurDto
    ){
        if(!statutChauffeurService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Statut_ChauffeurEntity statutChauffeurEntity = statutChauffeurMapper.mapFrom(statutChauffeurDto);
        Statut_ChauffeurEntity updatedStatutChauffeur = statutChauffeurService.partialUpdate(id, statutChauffeurEntity);
        return new ResponseEntity<>(statutChauffeurMapper.mapTo(updatedStatutChauffeur), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Statut_Chauffeur/{id}")
    public ResponseEntity deleteStatutChauffeur(@PathVariable("id") int id){
        statutChauffeurService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
