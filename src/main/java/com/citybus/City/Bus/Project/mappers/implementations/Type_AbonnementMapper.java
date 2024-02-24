package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Type_AbonnementDto;
import com.citybus.City.Bus.Project.domain.entities.Type_AbonnementEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class Type_AbonnementMapper implements Mapper<Type_AbonnementEntity, Type_AbonnementDto> {
    private ModelMapper modelMapper;

    public Type_AbonnementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Type_AbonnementDto mapTo(Type_AbonnementEntity typeAbonnementEntity) {
        return modelMapper.map(typeAbonnementEntity,Type_AbonnementDto.class);
    }

    @Override
    public Type_AbonnementEntity mapFrom(Type_AbonnementDto typeAbonnementDto) {
        return modelMapper.map(typeAbonnementDto, Type_AbonnementEntity.class);
    }
}
