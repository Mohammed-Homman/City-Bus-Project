package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.Statut_Chauffeur_Repository;
import com.citybus.City.Bus.Project.services.Statut_Chauffeur_Service;
import org.springframework.stereotype.Service;

@Service
public class Statut_Chauffeur_ServiceImpl implements Statut_Chauffeur_Service {
    private Statut_Chauffeur_Repository chauffeurRepository;

    public Statut_Chauffeur_ServiceImpl(Statut_Chauffeur_Repository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }
}
