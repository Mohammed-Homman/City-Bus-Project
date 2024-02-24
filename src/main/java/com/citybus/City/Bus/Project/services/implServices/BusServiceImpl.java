package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Bus_Entity;
import com.citybus.City.Bus.Project.repositories.BusRepository;
import com.citybus.City.Bus.Project.services.BusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;

    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
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

}
