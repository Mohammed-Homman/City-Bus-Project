package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.repositories.ClientRepository;
import com.citybus.City.Bus.Project.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.save((clientEntity));
    }

    @Override
    public List<ClientEntity> findAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public boolean isExists(int id) {
        return clientRepository.existsById(id);
    }
}
