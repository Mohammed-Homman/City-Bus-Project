package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Position_GPS_Dto;
import com.citybus.City.Bus.Project.domain.entities.Position_GPS_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.Position_GPS_Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Position_GPS_Controller {
    private Position_GPS_Service position_GPS_Service;
    private Mapper<Position_GPS_Entity, Position_GPS_Dto> position_GPS_Mapper;

    public Position_GPS_Controller(Position_GPS_Service position_GPS_Service, Mapper<Position_GPS_Entity, Position_GPS_Dto> position_GPS_Mapper) {
        this.position_GPS_Service = position_GPS_Service;
        this.position_GPS_Mapper = position_GPS_Mapper;
    }

    @PostMapping(path = "/Position_GPS")
    public ResponseEntity<Position_GPS_Dto> createPosition_GPS(@RequestBody Position_GPS_Dto position_GPS_Dto){
        try{
            Position_GPS_Entity position_GPS_Entity = position_GPS_Mapper.mapFrom(position_GPS_Dto);
            Position_GPS_Entity savedPosition_GPS_Entity = position_GPS_Service.save(position_GPS_Entity);
            return new ResponseEntity<>(position_GPS_Mapper.mapTo(savedPosition_GPS_Entity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path="/Position_GPS")
    public List<Position_GPS_Dto> listPosition_GPSs(){
        List<Position_GPS_Entity> position_GPS_Entities = position_GPS_Service.findAll();
        return position_GPS_Entities.stream().map(position_GPS_Mapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Position_GPS/{id}")
    public ResponseEntity<Position_GPS_Dto> getPosition_GPS(@PathVariable("id") int id){
        Optional<Position_GPS_Entity> foundPosition_GPS = position_GPS_Service.findOne(id);
        return foundPosition_GPS.map(position_GPS_Entity -> {
            Position_GPS_Dto position_GPS_Dto = position_GPS_Mapper.mapTo(position_GPS_Entity);
            return new ResponseEntity<>(position_GPS_Dto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Position_GPS/{id}")
    public ResponseEntity<Position_GPS_Dto> fullUpdatePosition_GPS(
            @PathVariable("id") int id,
            @RequestBody Position_GPS_Dto position_GPS_Dto){
        if (!position_GPS_Service.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        position_GPS_Dto.setId(id);
        Position_GPS_Entity position_GPS_Entity = position_GPS_Mapper.mapFrom(position_GPS_Dto);
        Position_GPS_Entity savedPosition_GPS_Entity = position_GPS_Service.save(position_GPS_Entity);
        return new ResponseEntity<>(position_GPS_Mapper.mapTo(savedPosition_GPS_Entity), HttpStatus.OK);
    }

    @PatchMapping(path = "/Position_GPS/{id}")
    public ResponseEntity<Position_GPS_Dto> partialUpdatePosition_GPS(
            @PathVariable("id") int id,
            @RequestBody Position_GPS_Dto position_GPS_Dto
    ){
        if(!position_GPS_Service.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Position_GPS_Entity position_GPS_Entity = position_GPS_Mapper.mapFrom(position_GPS_Dto);
        Position_GPS_Entity updatedPosition_GPS = position_GPS_Service.partialUpdate(id, position_GPS_Entity);
        return new ResponseEntity<>(position_GPS_Mapper.mapTo(updatedPosition_GPS), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Position_GPS/{id}")
    public ResponseEntity deletePosition_GPS(@PathVariable("id") int id){
        position_GPS_Service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

