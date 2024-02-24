package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class BusMapper implements Mapper<Bus_Entity, Bus_Dto> {
    private ModelMapper modelMapper;

    public BusMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Bus_Dto mapTo(Bus_Entity busEntity) {
        return modelMapper.map(busEntity, Bus_Dto.class);
    }

    @Override
    public Bus_Entity mapFrom(Bus_Dto busDto) {
        return modelMapper.map(busDto, Bus_Entity.class);
    }
}
