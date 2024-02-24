package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.Statut_Bus_Repository;
import com.citybus.City.Bus.Project.services.Statut_Bus_Service;
import org.springframework.stereotype.Service;

@Service
public class Statut_Bus_ServiceImpl implements Statut_Bus_Service {
    private Statut_Bus_Repository statutBusRepository;

    public Statut_Bus_ServiceImpl(Statut_Bus_Repository statutBusRepository) {
        this.statutBusRepository = statutBusRepository;
    }
}
