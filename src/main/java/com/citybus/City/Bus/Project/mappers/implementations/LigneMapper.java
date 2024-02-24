package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.LigneDto;
import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LigneMapper implements Mapper<LigneEntity, LigneDto> {
    private ModelMapper modelMapper;

    public LigneMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LigneDto mapTo(LigneEntity ligneEntity) {
        return modelMapper.map(ligneEntity, LigneDto.class);
    }

    @Override
    public LigneEntity mapFrom(LigneDto ligneDto) {
        return modelMapper.map(ligneDto, LigneEntity.class);
    }
}
