package com.citybus.City.Bus.Project.controllers;

import com.citybus.City.Bus.Project.domain.dto.ClientDto;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.mappers.Mapper;
import com.citybus.City.Bus.Project.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @GetMapping(path="/Client")
    public List<ClientDto> listClients(){
        List<ClientEntity> clientEntities = clientService.findAll();
        return clientEntities.stream().map(clientMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/Client/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") int id){
        Optional<ClientEntity> foundClient = clientService.findOne(id);
        return foundClient.map(clientEntity -> {
            ClientDto clientDto = clientMapper.mapTo(clientEntity);
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/Client/{id}")
    public ResponseEntity<ClientDto> fullUpdateClient(
            @PathVariable("id") int id,
            @RequestBody ClientDto clientDto){
        if (!clientService.isExists(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            clientDto.setId(id);
            ClientEntity clientEntity = clientMapper.mapFrom(clientDto);
            ClientEntity savedClientEntity = clientService.save(clientEntity);
            return new ResponseEntity<>(
                    clientMapper.mapTo(savedClientEntity),
                    HttpStatus.OK);
        }
    @PatchMapping(path = "/Client/{id}")
    public ResponseEntity<ClientDto> partialUpdate(
            @PathVariable("id") int id,
            @RequestBody ClientDto clientDto
    ){
        if(!clientService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClientEntity clientEntity = clientMapper.mapFrom(clientDto);
        ClientEntity updatedClient = clientService.partialUpdate(id, clientEntity);
        return new ResponseEntity<>(
                clientMapper.mapTo(updatedClient),
                HttpStatus.OK);
    }




}
