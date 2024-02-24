package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.BusRepository;
import com.citybus.City.Bus.Project.services.BusService;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;

    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
}
