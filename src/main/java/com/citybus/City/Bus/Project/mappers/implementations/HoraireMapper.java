package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.HoraireDto;
import com.citybus.City.Bus.Project.domain.entities.HoraireEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HoraireMapper implements Mapper<HoraireEntity, HoraireDto> {
    private ModelMapper modelMapper;

    public HoraireMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HoraireDto mapTo(HoraireEntity horaireEntity) {
        return modelMapper.map(horaireEntity,HoraireDto.class);
    }

    @Override
    public HoraireEntity mapFrom(HoraireDto horaireDto) {
        return modelMapper.map(horaireDto,HoraireEntity.class);
    }
}
