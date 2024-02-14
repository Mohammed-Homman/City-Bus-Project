package com.citybus.City.Bus.Project.mappers.implementations;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements Mapper<ClientEntity, ClientDto> {
private ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientDto mapTo(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientDto.class);
    }

    @Override
    public ClientEntity mapFrom(ClientDto clientDto) {
        return modelMapper.map(clientDto, ClientEntity.class);
    }
}
