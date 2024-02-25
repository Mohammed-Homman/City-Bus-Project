package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.dto.Type_Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.domain.entities.Type_Bus_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.Type_Bus_Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Type_BusController {
    private Type_Bus_Service typeBusService;
    private Mapper<Type_Bus_Entity, Type_Bus_Dto> typeBusMapper;

    public Type_BusController(Type_Bus_Service typeBusService, Mapper<Type_Bus_Entity, Type_Bus_Dto> typeBusMapper) {
        this.typeBusService = typeBusService;
        this.typeBusMapper = typeBusMapper;
    }

    @PostMapping(path = "/Type_Bus")
    public ResponseEntity<Type_Bus_Dto> createTypeBus(@RequestBody Type_Bus_Dto typeBusDto) {
        try {
            Type_Bus_Entity typeBusEntity = typeBusMapper.mapFrom(typeBusDto);
            Type_Bus_Entity savedTypeBusEntity = typeBusService.save(typeBusEntity);
            return new ResponseEntity<>(typeBusMapper.mapTo(savedTypeBusEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/Type_Bus")
    public List<Type_Bus_Dto> listTypeBuses() {
        List<Type_Bus_Entity> typeBusEntities = typeBusService.findAll();
        return typeBusEntities.stream().map(typeBusMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/Type_Bus/{id}")
    public ResponseEntity<Type_Bus_Dto> getTypeBus(@PathVariable("id") int id) {
        Optional<Type_Bus_Entity> foundTypeBus = typeBusService.findOne(id);
        return foundTypeBus.map(typeBusEntity -> new ResponseEntity<>(typeBusMapper.mapTo(typeBusEntity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Type_Bus/{id}")
    public ResponseEntity<Type_Bus_Dto> fullupdateTypeBus(@PathVariable("id") int id, @RequestBody Type_Bus_Dto typeBusDto) {
        if (!typeBusService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeBusDto.setId(id);
        Type_Bus_Entity typeBusEntity = typeBusMapper.mapFrom(typeBusDto);
        Type_Bus_Entity savedTypeBusEntity = typeBusService.save(typeBusEntity);
        return new ResponseEntity<>(typeBusMapper.mapTo(savedTypeBusEntity), HttpStatus.OK);
    }
    @PatchMapping(path = "/Type_Bus/{id}")
    public ResponseEntity<Type_Bus_Dto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody Type_Bus_Dto typeBusDto){
        if (!typeBusService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type_Bus_Entity typeBusEntity = typeBusMapper.mapFrom(typeBusDto);
        Type_Bus_Entity savedTypeBusEntity = typeBusService.partialUpdate(typeBusEntity);
        return new ResponseEntity<>(
                typeBusMapper.mapTo(savedTypeBusEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/Type_Bus/{id}")
    public ResponseEntity deleteTypeBus(@PathVariable("id") int id) {
        typeBusService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
