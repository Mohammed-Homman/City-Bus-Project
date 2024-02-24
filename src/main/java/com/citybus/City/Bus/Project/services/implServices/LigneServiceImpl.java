package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.LigneRepository;
import com.citybus.City.Bus.Project.services.LigneService;

public class LigneServiceImpl implements LigneService {
    private LigneRepository ligneRepository;

    public LigneServiceImpl(LigneRepository ligneRepository) {
        this.ligneRepository = ligneRepository;
    }
}
