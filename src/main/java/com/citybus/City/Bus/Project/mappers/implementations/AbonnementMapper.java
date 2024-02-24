package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.AbonnementDto;
import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class AbonnementMapper implements Mapper<AbonnementEntity, AbonnementDto> {
    private ModelMapper modelMapper;

    public AbonnementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public AbonnementDto mapTo(AbonnementEntity abonnementEntity) {
        return modelMapper.map(abonnementEntity, AbonnementDto.class);    }

    @Override
    public AbonnementEntity mapFrom(AbonnementDto abonnementDto) {
        return modelMapper.map(abonnementDto, AbonnementEntity.class);
    }
}
