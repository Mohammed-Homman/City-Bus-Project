package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private ClientService clientService;
    private Mapper<ClientEntity, ClientDto> clientMapper;

    public ClientController(ClientService clientService, Mapper<ClientEntity, ClientDto> clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping(path = "/Client")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        ClientEntity clientEntity = clientMapper.mapFrom(clientDto);
        ClientEntity savedClientEntity = clientService.save(clientEntity);
        return new ResponseEntity<>(clientMapper.mapTo(savedClientEntity), HttpStatus.CREATED);
    }

}
