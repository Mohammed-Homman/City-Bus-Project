package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.Type_Bus_Entity;
import com.citybus.City.Bus.Project.repositories.Type_Abonnement_Repository;
import com.citybus.City.Bus.Project.repositories.Type_Bus_Repository;
import com.citybus.City.Bus.Project.services.Type_Bus_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Type_Bus_ServiceImpl implements Type_Bus_Service {
    private Type_Bus_Repository type_bus_repository;

    public Type_Bus_ServiceImpl(Type_Bus_Repository type_bus_repository) {
        this.type_bus_repository = type_bus_repository;
    }

    @Override
    public Type_Bus_Entity save(Type_Bus_Entity typeBusEntity) {
        return type_bus_repository.save(typeBusEntity);
    }

    @Override
    public List<Type_Bus_Entity> findAll() {
        return StreamSupport.stream(type_bus_repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<Type_Bus_Entity> findOne(int id) {
        return type_bus_repository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return type_bus_repository.existsById(id);
    }

    @Override
    public Type_Bus_Entity partialUpdate(Type_Bus_Entity typeBusEntity) {
        return null;
    }

    @Override
    public void delete(int id) {
        type_bus_repository.deleteById(id);
    }
}
