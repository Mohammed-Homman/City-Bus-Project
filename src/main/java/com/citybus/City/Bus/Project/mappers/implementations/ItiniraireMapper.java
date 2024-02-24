package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Itiniraire_Dto;
import com.citybus.City.Bus.Project.domain.entities.Itiniraire_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class ItiniraireMapper implements Mapper<Itiniraire_Entity, Itiniraire_Dto> {
    private ModelMapper modelMapper;

    public ItiniraireMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Itiniraire_Dto mapTo(Itiniraire_Entity itiniraireEntity) {
        return modelMapper.map(itiniraireEntity, Itiniraire_Dto.class);
    }

    @Override
    public Itiniraire_Entity mapFrom(Itiniraire_Dto itiniraireDto) {
        return modelMapper.map(itiniraireDto, Itiniraire_Entity.class);
    }
}
