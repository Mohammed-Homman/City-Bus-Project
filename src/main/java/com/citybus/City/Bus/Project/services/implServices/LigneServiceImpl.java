package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.LigneRepository;
import com.citybus.City.Bus.Project.services.LigneService;
import org.springframework.stereotype.Service;

@Service
public class LigneServiceImpl implements LigneService {
    private LigneRepository ligneRepository;

    public LigneServiceImpl(LigneRepository ligneRepository) {
        this.ligneRepository = ligneRepository;
    }
}
