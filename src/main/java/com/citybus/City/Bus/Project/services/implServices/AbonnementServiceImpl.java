package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.AbonnementRepository;
import com.citybus.City.Bus.Project.services.AbonnementService;
import org.springframework.stereotype.Service;

@Service
public class AbonnementServiceImpl implements AbonnementService {
    private AbonnementRepository abonnementRepository;

    public AbonnementServiceImpl(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }
}
