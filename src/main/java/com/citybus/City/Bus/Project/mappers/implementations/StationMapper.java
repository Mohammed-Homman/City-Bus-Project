package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Station_Dto;
import com.citybus.City.Bus.Project.domain.entities.Station_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StationMapper implements Mapper<Station_Entity, Station_Dto> {
    private ModelMapper modelMapper;

    public StationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Station_Dto mapTo(Station_Entity stationEntity) {
        return modelMapper.map(stationEntity, Station_Dto.class);
    }

    @Override
    public Station_Entity mapFrom(Station_Dto stationDto) {
        return modelMapper.map(stationDto, Station_Entity.class);
    }
}
