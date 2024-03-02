package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.Point_de_venteDto;
import com.citybus.City.Bus.Project.domain.entities.Point_de_venteEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Point_de_venteMapper implements Mapper<Point_de_venteEntity, Point_de_venteDto> {
    private ModelMapper modelMapper;

    public Point_de_venteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Point_de_venteDto mapTo(Point_de_venteEntity pointDeVenteEntity) {
        return modelMapper.map(pointDeVenteEntity, Point_de_venteDto.class);
    }

    @Override
    public Point_de_venteEntity mapFrom(Point_de_venteDto pointDeVenteDto) {
        return modelMapper.map(pointDeVenteDto,Point_de_venteEntity.class);
    }
}
