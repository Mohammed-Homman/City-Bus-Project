package com.citybus.City.Bus.Project.services;

import com.citybus.City.Bus.Project.domain.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    ClientEntity save(ClientEntity clientEntity);

    List<ClientEntity> findAll();

    boolean isExists(int id);
}
