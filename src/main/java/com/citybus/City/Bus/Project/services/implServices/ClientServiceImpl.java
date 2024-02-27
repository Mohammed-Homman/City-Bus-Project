package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.repositories.ClientRepository;
import com.citybus.City.Bus.Project.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return clientRepository.save(clientEntity);
    }

    @Override
    public List<ClientEntity> findAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientEntity> findOne(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientEntity partialUpdate(String id, ClientEntity clientEntity) {
        clientEntity.setId(id);

        return clientRepository.findById(id).map(existingClient ->{
            Optional.ofNullable(clientEntity.getNom()).ifPresent(existingClient::setNom);
            Optional.ofNullable(clientEntity.getPrenom()).ifPresent(existingClient::setPrenom);
            Optional.ofNullable(clientEntity.getEmail()).ifPresent(existingClient::setEmail);
            Optional.ofNullable(clientEntity.getTele()).ifPresent(existingClient::setTele);
            Optional.ofNullable(clientEntity.getAdresse()).ifPresent(existingClient::setAdresse);
            Optional.ofNullable(clientEntity.getEmploi()).ifPresent(existingClient::setEmploi);
            Optional.ofNullable(clientEntity.getCni()).ifPresent(existingClient::setCni);
            Optional.ofNullable(clientEntity.getMot_de_passe()).ifPresent(existingClient::setMot_de_passe);
            Optional.ofNullable(clientEntity.getDateNaissance()).ifPresent(existingClient::setDateNaissance);
            Optional.ofNullable(clientEntity.getSex()).ifPresent(existingClient::setSex);
            Optional.ofNullable(clientEntity.getDateInscription()).ifPresent(existingClient::setDateInscription);

            return clientRepository.save(existingClient);
        }).orElseThrow(() -> new RuntimeException("Client does not exist"));
    }
    ;

    @Override
    public boolean isExists(String id) {
        return clientRepository.existsById(id);
    }

    @Override
    public void delete(String id) {
        clientRepository.deleteById(id);
    }
}
