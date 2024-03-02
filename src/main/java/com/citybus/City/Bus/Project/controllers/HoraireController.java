package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.HoraireDto;
import com.citybus.City.Bus.Project.domain.entities.HoraireEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.HoraireService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class HoraireController {
    private HoraireService horaireService;
    private Mapper<HoraireEntity, HoraireDto> horaireMapper;

    public HoraireController(HoraireService horaireService, Mapper<HoraireEntity, HoraireDto> horaireMapper) {
        this.horaireService = horaireService;
        this.horaireMapper = horaireMapper;
    }
    @PostMapping(path = "/Horaire")
    public ResponseEntity<HoraireDto> createHoraire(@RequestBody HoraireDto horaireDto) {
        try {
            HoraireEntity horaireEntity = horaireMapper.mapFrom(horaireDto);
            HoraireEntity savedHoraireEntity = horaireService.save(horaireEntity);
            return new ResponseEntity<>(horaireMapper.mapTo(savedHoraireEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/Horaire")
    public List<HoraireDto> listHoraires() {
        List<HoraireEntity> horaireEntities = horaireService.findAll();
        return horaireEntities.stream().map(horaireMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Horaire/{id}")
    public ResponseEntity<HoraireDto> getHoraire(@PathVariable("id") int id) {
        Optional<HoraireEntity> foundHoraire = horaireService.findOne(id);
        return foundHoraire.map(horaireEntity -> {
            HoraireDto horaireDto = horaireMapper.mapTo(horaireEntity);
            return new ResponseEntity<>(horaireDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Horaire/{id}")
    public ResponseEntity<HoraireDto> fullUpdateHoraire(
            @PathVariable("id") int id,
            @RequestBody HoraireDto horaireDto) {
        if (!horaireService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        horaireDto.setId(id);
        HoraireEntity horaireEntity = horaireMapper.mapFrom(horaireDto);
        HoraireEntity savedHoraireEntity = horaireService.save(horaireEntity);
        return new ResponseEntity<>(horaireMapper.mapTo(savedHoraireEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Horaire/{id}")
    public ResponseEntity<HoraireDto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody HoraireDto horaireDto) {
        if (!horaireService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HoraireEntity horaireEntity = horaireMapper.mapFrom(horaireDto);
        HoraireEntity updatedHoraire = horaireService.partialUpdate(id, horaireEntity);
        return new ResponseEntity<>(horaireMapper.mapTo(updatedHoraire), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Horaire/{id}")
    public ResponseEntity deleteHoraire(@PathVariable("id") int id) {
        horaireService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
