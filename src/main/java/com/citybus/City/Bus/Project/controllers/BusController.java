package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Bus_Dto;
import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BusController {
    private BusService busService;
    private Mapper<Bus_Entity, Bus_Dto> busMapper;

    public BusController(BusService busService, Mapper<Bus_Entity, Bus_Dto> busMapper) {
        this.busService = busService;
        this.busMapper = busMapper;
    }
    @PostMapping(path = "/Bus")
    public ResponseEntity<Bus_Dto> createBus(@RequestBody Bus_Dto busDto){
        Bus_Entity busEntity = busMapper.mapFrom(busDto);
        Bus_Entity savedBusEntity = busService.save(busEntity);
        return new ResponseEntity<>(busMapper.mapTo(savedBusEntity), HttpStatus.CREATED);
    }
    @GetMapping(path="/Bus")
    public List<Bus_Dto> listBus(){
        List<Bus_Entity> busEntities = busService.findAll();
        return busEntities.stream().map(busMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/Bus/{id}")
    public ResponseEntity<Bus_Dto> getBus(@PathVariable("id") int id){
        Optional<Bus_Entity> foundBus = busService.findOne(id);
        return foundBus.map(busEntity -> {
            Bus_Dto busDto = busMapper.mapTo(busEntity);
            return new ResponseEntity<>(busDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Bus/{id}")
    public ResponseEntity<Bus_Dto> fullUpdateBus(
            @PathVariable("id") int id,
            @RequestBody Bus_Dto busDto){
        if (!busService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        busDto.setId(id);
        Bus_Entity busEntity = busMapper.mapFrom(busDto);
        Bus_Entity savedBusEntity = busService.save(busEntity);
        return new ResponseEntity<>(
                busMapper.mapTo(savedBusEntity),
                HttpStatus.OK);
    }
    @PatchMapping(path = "/Bus/{id}")
    public ResponseEntity<Bus_Dto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody Bus_Dto busDto
    ){
        if(!busService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Bus_Entity busEntity = busMapper.mapFrom(busDto);
        Bus_Entity updatedBus = busService.partialUpdate(id, busEntity);
        return new ResponseEntity<>(
                busMapper.mapTo(updatedBus),
                HttpStatus.OK);
    }}
