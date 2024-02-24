package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientEntity save(ClientEntity clientEntity);

    List<ClientEntity> findAll();

    boolean isExists(int id);

    Optional<ClientEntity> findOne(int id);

    ClientEntity partialUpdate(int id, ClientEntity clientEntity);

    void delete(int id);
}
