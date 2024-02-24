package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.StationRepository;
import com.citybus.City.Bus.Project.services.StationService;

public class StationServiceImpl implements StationService {
    private StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
}
