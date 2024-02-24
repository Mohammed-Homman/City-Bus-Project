package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.ArretRepository;
import com.citybus.City.Bus.Project.services.ArretService;
import org.springframework.stereotype.Service;

@Service
public class ArretServiceImpl implements ArretService {
    private ArretRepository arretRepository;

    public ArretServiceImpl(ArretRepository arretRepository) {
        this.arretRepository = arretRepository;
    }
}
