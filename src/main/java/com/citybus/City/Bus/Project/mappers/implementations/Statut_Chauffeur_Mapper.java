package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Statut_ChauffeurDto;
import com.citybus.City.Bus.Project.domain.entities.Statut_Bus_Entity;
import com.citybus.City.Bus.Project.domain.entities.Statut_ChauffeurEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Statut_Chauffeur_Mapper implements Mapper<Statut_ChauffeurEntity, Statut_ChauffeurDto> {
    private ModelMapper modelMapper;

    public Statut_Chauffeur_Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Statut_ChauffeurDto mapTo(Statut_ChauffeurEntity statutChauffeurEntity) {
        return modelMapper.map(statutChauffeurEntity, Statut_ChauffeurDto.class);
    }

    @Override
    public Statut_ChauffeurEntity mapFrom(Statut_ChauffeurDto statutChauffeurDto) {
        return modelMapper.map(statutChauffeurDto, Statut_ChauffeurEntity.class);
    }
}
