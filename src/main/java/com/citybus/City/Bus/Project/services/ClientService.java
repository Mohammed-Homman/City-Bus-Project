package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientEntity save(ClientEntity clientEntity);

    List<ClientEntity> findAll();

    boolean isExists(String id);


    ClientEntity partialUpdate(String id, ClientEntity clientEntity);

    void delete(String id);

    Optional<ClientEntity> findOne(String id);
}
