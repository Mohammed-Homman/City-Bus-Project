package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.ItiniraireRepository;
import com.citybus.City.Bus.Project.services.ItiniraireService;

public class ItiniraireServiceImpl implements ItiniraireService {
    private ItiniraireRepository itiniraireRepository;

    public ItiniraireServiceImpl(ItiniraireRepository itiniraireRepository) {
        this.itiniraireRepository = itiniraireRepository;
    }
}
