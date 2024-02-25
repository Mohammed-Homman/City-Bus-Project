package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Station_Dto;
import com.citybus.City.Bus.Project.domain.entities.Station_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.StationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StationController {
    private StationService stationService;
    private Mapper<Station_Entity, Station_Dto> stationMapper;

    public StationController(StationService stationService, Mapper<Station_Entity, Station_Dto> stationMapper) {
        this.stationService = stationService;
        this.stationMapper = stationMapper;
    }

    @PostMapping(path = "/Station")
    public ResponseEntity<Station_Dto> createStation(@RequestBody Station_Dto stationDto){
        try{
            Station_Entity stationEntity = stationMapper.mapFrom(stationDto);
            Station_Entity savedStationEntity = stationService.save(stationEntity);
            return new ResponseEntity<>(stationMapper.mapTo(savedStationEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path="/Station")
    public List<Station_Dto> listStations(){
        List<Station_Entity> stationEntities = stationService.findAll();
        return stationEntities.stream().map(stationMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Station/{id}")
    public ResponseEntity<Station_Dto> getStation(@PathVariable("id") int id){
        Optional<Station_Entity> foundStation = stationService.findOne(id);
        return foundStation.map(stationEntity -> {
            Station_Dto stationDto = stationMapper.mapTo(stationEntity);
            return new ResponseEntity<>(stationDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Station/{id}")
    public ResponseEntity<Station_Dto> fullUpdateStation(
            @PathVariable("id") int id,
            @RequestBody Station_Dto stationDto){
        if (!stationService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stationDto.setId(id);
        Station_Entity stationEntity = stationMapper.mapFrom(stationDto);
        Station_Entity savedStationEntity = stationService.save(stationEntity);
        return new ResponseEntity<>(stationMapper.mapTo(savedStationEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Station/{id}")
    public ResponseEntity<Station_Dto> partialUpdateStation(
            @PathVariable("id") int id,
            @RequestBody Station_Dto stationDto
    ){
        if(!stationService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Station_Entity stationEntity = stationMapper.mapFrom(stationDto);
        Station_Entity updatedStation = stationService.partialUpdate(id, stationEntity);
        return new ResponseEntity<>(stationMapper.mapTo(updatedStation), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Station/{id}")
    public ResponseEntity deleteStation(@PathVariable("id") int id){
        stationService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

