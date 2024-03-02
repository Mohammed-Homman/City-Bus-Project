package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ChauffeurDto;
import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.ChauffeurService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChauffeurController {
    private ChauffeurService chauffeurService;
    private Mapper<ChauffeurEntity, ChauffeurDto> chauffeurMapper;

    public ChauffeurController(ChauffeurService chauffeurService, Mapper<ChauffeurEntity, ChauffeurDto> chauffeurMapper) {
        this.chauffeurService = chauffeurService;
        this.chauffeurMapper = chauffeurMapper;
    }
    @PostMapping(path = "/Chauffeur")
    public ResponseEntity<ChauffeurDto> createChauffeur(@RequestBody ChauffeurDto chauffeurDto){
        try{
            ChauffeurEntity chauffeurEntity = chauffeurMapper.mapFrom(chauffeurDto);
            ChauffeurEntity savedChauffeurEntity = chauffeurService.save(chauffeurEntity);
            return new ResponseEntity<>(chauffeurMapper.mapTo(savedChauffeurEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path="/Chauffeur")
    public List<ChauffeurDto> listChauffeur(){
        List<ChauffeurEntity> chauffeurEntities = chauffeurService.findAll();
        return chauffeurEntities.stream().map(chauffeurMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/Chauffeur/{id}")
    public ResponseEntity<ChauffeurDto> getChauffeur(@PathVariable("id") int id){
        Optional<ChauffeurEntity> foundChauffeur = chauffeurService.findOne(id);
        return foundChauffeur.map(chauffeurEntity -> {
            ChauffeurDto chauffeurDto = chauffeurMapper.mapTo(chauffeurEntity);
            return new ResponseEntity<>(chauffeurDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Chauffeur/{id}")
    public ResponseEntity<ChauffeurDto> fullUpdateChauffeur(
            @PathVariable("id") int id,
            @RequestBody ChauffeurDto chauffeurDto){
        if (!chauffeurService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        chauffeurDto.setId(id);
        ChauffeurEntity chauffeurEntity = chauffeurMapper.mapFrom(chauffeurDto);
        ChauffeurEntity savedchauffeurEntity = chauffeurService.save(chauffeurEntity);
        return new ResponseEntity<>(
                chauffeurMapper.mapTo(savedchauffeurEntity),
                HttpStatus.OK);
    }
    @PatchMapping(path = "/Chauffeur/{id}")
    public ResponseEntity<ChauffeurDto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody ChauffeurDto chauffeurDto
    ){
        if(!chauffeurService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ChauffeurEntity chauffeurEntity = chauffeurMapper.mapFrom(chauffeurDto);
        ChauffeurEntity updatedChauffeur = chauffeurService.partialUpdate(id, chauffeurEntity);
        return new ResponseEntity<>(
                chauffeurMapper.mapTo(updatedChauffeur),
                HttpStatus.OK);
    }
    @DeleteMapping(path = "/Chauffeur/{id}")
    public ResponseEntity deleteChauffeur(@PathVariable("id") int id){
        chauffeurService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/Chauffeur/{chauffeurId}/updateStatut/{statutId}")
    public ResponseEntity<?> updateChauffeurStatut(@PathVariable("chauffeurId") int chauffeurId, @PathVariable("statutId") int statutId){
        try {
            chauffeurService.updateBusStatut(chauffeurId, statutId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update subscription status.");
        }
    }
    @PutMapping(path = "/Chauffeur/{chauffeurId}/addLigne/{ligneId}")
    public ResponseEntity<Void> addLigneToChauffeur(@PathVariable("chauffeurId") int chauffeurId, @PathVariable("ligneId") int ligneId) {
        chauffeurService.addLigneToChauffeur(ligneId, chauffeurId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/Chauffeur/{chauffeurId}/removeLigne/{ligneId}")
    public ResponseEntity<Void> removeLigneFromChauffeur(@PathVariable("chauffeurId") int chauffeurId,
                                                         @PathVariable("ligneId") int ligneId) {
        try {
            // Vérifier si le chauffeur existe
            if (!chauffeurService.isExists(chauffeurId)) {
                return ResponseEntity.notFound().build();
            }

            // Supprimer la ligne du chauffeur
            chauffeurService.removeLigneFromChauffeur(ligneId, chauffeurId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
