package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Type_Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.Type_Bus_Entity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Type_Bus_Mapper implements Mapper<Type_Bus_Entity, Type_Bus_Dto> {
    private ModelMapper modelMapper;

    public Type_Bus_Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Type_Bus_Dto mapTo(Type_Bus_Entity typeBusEntity) {
        return modelMapper.map(typeBusEntity,Type_Bus_Dto.class);
    }

    @Override
    public Type_Bus_Entity mapFrom(Type_Bus_Dto typeBusDto) {
        return modelMapper.map(typeBusDto,Type_Bus_Entity.class);
    }
}
