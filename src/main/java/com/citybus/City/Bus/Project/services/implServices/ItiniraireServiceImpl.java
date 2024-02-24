package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.ItiniraireRepository;
import com.citybus.City.Bus.Project.services.ItiniraireService;
import org.springframework.stereotype.Service;

@Service
public class ItiniraireServiceImpl implements ItiniraireService {
    private ItiniraireRepository itiniraireRepository;

    public ItiniraireServiceImpl(ItiniraireRepository itiniraireRepository) {
        this.itiniraireRepository = itiniraireRepository;
    }
}
