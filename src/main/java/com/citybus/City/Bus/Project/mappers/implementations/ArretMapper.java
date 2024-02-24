package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.ArretDto;
import com.citybus.City.Bus.Project.domain.entities.ArretEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArretMapper implements Mapper<ArretEntity, ArretDto> {
    private ModelMapper modelMapper;

    public ArretMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ArretDto mapTo(ArretEntity arretEntity) {
        return modelMapper.map(arretEntity, ArretDto.class);
    }

    @Override
    public ArretEntity mapFrom(ArretDto arretDto) {
        return modelMapper.map(arretDto, ArretEntity.class);
    }
}
