package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.*;
import com.citybus.City.Bus.Project.repositories.BusRepository;
import com.citybus.City.Bus.Project.repositories.Position_GPS_Repository;
import com.citybus.City.Bus.Project.repositories.Statut_Bus_Repository;
import com.citybus.City.Bus.Project.repositories.Type_Bus_Repository;
import com.citybus.City.Bus.Project.services.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;
    private Type_Bus_Repository type_bus_repository;
    private Statut_Bus_Repository statut_bus_repository;
    private Position_GPS_Repository positionGpsRepository;

    public BusServiceImpl(BusRepository busRepository, Type_Bus_Repository type_bus_repository, Statut_Bus_Repository statut_bus_repository, Position_GPS_Repository positionGpsRepository) {
        this.busRepository = busRepository;
        this.type_bus_repository = type_bus_repository;
        this.statut_bus_repository = statut_bus_repository;
        this.positionGpsRepository = positionGpsRepository;
    }

    @Override
    public Bus_Entity save(Bus_Entity busEntity) {
        return busRepository.save((busEntity));
    }

    @Override
    public List<Bus_Entity> findAll() {
        return StreamSupport.stream(busRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Bus_Entity> findOne(int id) {
        return busRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return busRepository.existsById(id);
    }

    @Override
    public Bus_Entity partialUpdate(int id, Bus_Entity busEntity) {
        return busRepository.findById(id).map(existingBus->{
            Optional.ofNullable(busEntity.getMarque()).ifPresent(existingBus::setMarque);
            return busRepository.save(existingBus);
        }).orElseThrow(()-> new RuntimeException("Bus does not exist"));
    }

    @Override
    public void delete(int id) {
        busRepository.deleteById(id);
    }

    @Override
    public void updateBusType(int busId, int typeId) {
        Bus_Entity bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found"));

        Type_Bus_Entity newType = type_bus_repository.findById(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type_Bus not found"));

        bus.setTypeBusEntity(newType);
        busRepository.save(bus);
    }

    @Override
    public void updateBusStatut(int busId, int statutId) {
        Bus_Entity bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found"));

        Statut_Bus_Entity newStatut = statut_bus_repository.findById(statutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Statut_Bus not found"));

        bus.setStatutBusEntity(newStatut);
        busRepository.save(bus);
    }

    @Override
    public void updateBusPosition(int busId, int positionId) {
        Bus_Entity bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found"));

        Position_GPS_Entity newPosition = positionGpsRepository.findById(positionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Position not found"));

        bus.setPositionGpsEntity(newPosition);
        busRepository.save(bus);
    }
}
