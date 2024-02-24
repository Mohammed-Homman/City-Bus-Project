package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.BusService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {
    private BusService busService;
    private Mapper<Bus_Entity, Bus_Dto> busMapper;

    public BusController(BusService busService, Mapper<Bus_Entity, Bus_Dto> busMapper) {
        this.busService = busService;
        this.busMapper = busMapper;
    }
}
