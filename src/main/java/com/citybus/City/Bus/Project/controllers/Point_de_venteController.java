package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.dto.Point_de_venteDto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.domain.entities.Point_de_venteEntity;
import com.citybus.City.Bus.Project.mappers.implementations.Point_de_venteMapper;
import com.citybus.City.Bus.Project.services.Point_de_venteService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Point_de_venteController {
    private Point_de_venteService pointDeVenteService;
    private Point_de_venteMapper pointDeVenteMapper;

    public Point_de_venteController(Point_de_venteService pointDeVenteService, Point_de_venteMapper pointDeVenteMapper) {
        this.pointDeVenteService = pointDeVenteService;
        this.pointDeVenteMapper = pointDeVenteMapper;
    }

    @PostMapping(path = "/PointDeVente")
    public ResponseEntity<Point_de_venteDto> createPointDeVente(@RequestBody Point_de_venteDto pointDeVenteDto) {
        try {
            Point_de_venteEntity pointDeVenteEntity = pointDeVenteMapper.mapFrom(pointDeVenteDto);
            Point_de_venteEntity savedPointDeVenteEntity = pointDeVenteService.save(pointDeVenteEntity);
            return new ResponseEntity<>(pointDeVenteMapper.mapTo(savedPointDeVenteEntity), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation gracefully
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path="/PointDeVente")
    public List<Point_de_venteDto> listPointDeVentes(){
        List<Point_de_venteEntity> pointDeVenteEntities = pointDeVenteService.findAll();
        return pointDeVenteEntities.stream().map(pointDeVenteMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/PointDeVente/{id}")
    public ResponseEntity<Point_de_venteDto> getPointDeVente(@PathVariable("id") int id){
        Optional<Point_de_venteEntity> foundPointDeVente = pointDeVenteService.findOne(id);
        return foundPointDeVente.map(pointDeVenteEntity -> {
            Point_de_venteDto pointDeVenteDto = pointDeVenteMapper.mapTo(pointDeVenteEntity);
            return new ResponseEntity<>(pointDeVenteDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/PointDeVente/{id}")
    public ResponseEntity<Point_de_venteDto> fullUpdatePointDeVente(
            @PathVariable("id") int id,
            @RequestBody Point_de_venteDto pointDeVenteDto){
        if (!pointDeVenteService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pointDeVenteDto.setId(id);
        Point_de_venteEntity pointDeVenteEntity = pointDeVenteMapper.mapFrom(pointDeVenteDto);
        Point_de_venteEntity savedClientEntity = pointDeVenteService.save(pointDeVenteEntity);
        return new ResponseEntity<>(
                pointDeVenteMapper.mapTo(savedClientEntity),
                HttpStatus.OK);
    }
    @PatchMapping(path = "/PointDeVente/{id}")
    public ResponseEntity<Point_de_venteDto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody Point_de_venteDto pointDeVenteDto
    ){
        if(!pointDeVenteService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Point_de_venteEntity pointDeVenteEntity = pointDeVenteMapper.mapFrom(pointDeVenteDto);
        Point_de_venteEntity updatedPointDeVente = pointDeVenteService.partialUpdate(id, pointDeVenteEntity);
        return new ResponseEntity<>(
                pointDeVenteMapper.mapTo(updatedPointDeVente),
                HttpStatus.OK);
    }
    @DeleteMapping(path = "/PointDeVente/{id}")
    public ResponseEntity deletePointDeVente(@PathVariable("id") int id){
        pointDeVenteService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
