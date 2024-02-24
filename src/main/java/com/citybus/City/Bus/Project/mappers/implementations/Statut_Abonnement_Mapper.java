package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Statut_AbonnementDto;
import com.citybus.City.Bus.Project.domain.dto.Statut_Bus_Dto;
import com.citybus.City.Bus.Project.domain.entities.Statut_AbonnementEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class Statut_Abonnement_Mapper implements Mapper<Statut_AbonnementEntity, Statut_AbonnementDto> {
    private ModelMapper modelMapper;

    public Statut_Abonnement_Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Statut_AbonnementDto mapTo(Statut_AbonnementEntity statutAbonnementEntity) {
        return modelMapper.map(statutAbonnementEntity, Statut_AbonnementDto.class);
    }

    @Override
    public Statut_AbonnementEntity mapFrom(Statut_AbonnementDto statutAbonnementDto) {
        return modelMapper.map(statutAbonnementDto, Statut_AbonnementEntity.class);
    }
}
