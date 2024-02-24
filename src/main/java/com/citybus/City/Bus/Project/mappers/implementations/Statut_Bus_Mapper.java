package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Statut_Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.Statut_Bus_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class Statut_Bus_Mapper implements Mapper<Statut_Bus_Entity, Statut_Bus_Dto> {
    private ModelMapper modelMapper;

    public Statut_Bus_Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Statut_Bus_Dto mapTo(Statut_Bus_Entity statutBusEntity) {
        return modelMapper.map(statutBusEntity, Statut_Bus_Dto.class);
    }

    @Override
    public Statut_Bus_Entity mapFrom(Statut_Bus_Dto statutBusDto) {
        return modelMapper.map(statutBusDto, Statut_Bus_Entity.class);
    }
}
