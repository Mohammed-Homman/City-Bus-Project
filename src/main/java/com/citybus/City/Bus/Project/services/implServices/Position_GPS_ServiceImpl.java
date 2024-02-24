package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.Position_GPS_Repository;
import com.citybus.City.Bus.Project.services.Position_GPS_Service;
import org.springframework.stereotype.Service;

@Service
public class Position_GPS_ServiceImpl implements Position_GPS_Service {
    private Position_GPS_Repository positionGpsRepository;

    public Position_GPS_ServiceImpl(Position_GPS_Repository positionGpsRepository) {
        this.positionGpsRepository = positionGpsRepository;
    }
}
