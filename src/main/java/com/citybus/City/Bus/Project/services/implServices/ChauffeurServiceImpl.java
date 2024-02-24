package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.ChauffeurRepository;
import com.citybus.City.Bus.Project.services.ChauffeurService;

public class ChauffeurServiceImpl implements ChauffeurService {
    private ChauffeurRepository chauffeurRepository;

    public ChauffeurServiceImpl(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }
}
