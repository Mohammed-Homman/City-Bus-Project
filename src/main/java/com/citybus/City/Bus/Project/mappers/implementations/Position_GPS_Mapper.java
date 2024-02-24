package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Position_GPS_Dto;
import com.citybus.City.Bus.Project.domain.entities.Position_GPS_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class Position_GPS_Mapper implements Mapper<Position_GPS_Entity,Position_GPS_Dto> {
    private ModelMapper modelMapper;

    public Position_GPS_Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Position_GPS_Dto mapTo(Position_GPS_Entity positionGpsEntity) {
        return modelMapper.map(positionGpsEntity, Position_GPS_Dto.class);
    }

    @Override
    public Position_GPS_Entity mapFrom(Position_GPS_Dto positionGpsDto) {
        return modelMapper.map(positionGpsDto, Position_GPS_Entity.class);
    }
}
