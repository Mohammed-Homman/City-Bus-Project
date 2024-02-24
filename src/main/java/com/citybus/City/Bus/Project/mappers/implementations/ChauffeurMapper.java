package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.ChauffeurDto;
import com.citybus.City.Bus.Project.domain.entities.ChauffeurEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ChauffeurMapper implements Mapper<ChauffeurEntity, ChauffeurDto> {
    private ModelMapper modelMapper;

    public ChauffeurMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ChauffeurDto mapTo(ChauffeurEntity chauffeurEntity) {
        return modelMapper.map(chauffeurEntity, ChauffeurDto.class);
    }

    @Override
    public ChauffeurEntity mapFrom(ChauffeurDto chauffeurDto) {
        return modelMapper.map(chauffeurDto, ChauffeurEntity.class);
    }
}
