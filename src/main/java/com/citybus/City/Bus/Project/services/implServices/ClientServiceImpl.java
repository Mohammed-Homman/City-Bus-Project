package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.AbonnementEntity;
import com.citybus.City.Bus.Project.domain.entities.ClientEntity;
import com.citybus.City.Bus.Project.repositories.AbonnementRepository;
import com.citybus.City.Bus.Project.repositories.ClientRepository;
import com.citybus.City.Bus.Project.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private AbonnementRepository abonnementRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AbonnementRepository abonnementRepository) {
        this.clientRepository = clientRepository;
        this.abonnementRepository = abonnementRepository;
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
    public Optional<ClientEntity> findOne(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientEntity partialUpdate(int id, ClientEntity clientEntity) {
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
    public boolean isExists(int id) {
        return clientRepository.existsById(id);
    }

    @Override
    public void delete(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void addAbonnementToClient(int clientId, int abonnementId) {
        ClientEntity client = clientRepository.findById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        AbonnementEntity abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonnement not found"));

        client.getAbonnements().add(abonnement);
        abonnement.getClients().add(client);

        clientRepository.save(client);
        abonnementRepository.save(abonnement);
    }

    @Override
    public void removeAbonnementFromClient(int clientId, int abonnementId) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(NoSuchElementException::new);

        AbonnementEntity abonnementToRemove = null;
        for (AbonnementEntity abonnement : client.getAbonnements()) {
            if (abonnement.getId() == abonnementId) {
                abonnementToRemove = abonnement;
                break;
            }
        }

        if (abonnementToRemove != null) {
            client.getAbonnements().remove(abonnementToRemove);
            clientRepository.save(client);
        } else {
            throw new NoSuchElementException("Abonnement not found for the client");
        }
    }
}
