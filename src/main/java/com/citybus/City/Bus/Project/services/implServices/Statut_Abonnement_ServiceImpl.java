package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.Statut_Abonnement_Repository;
import com.citybus.City.Bus.Project.services.Statut_Abonnement_Service;
import org.springframework.stereotype.Service;

@Service
public class Statut_Abonnement_ServiceImpl implements Statut_Abonnement_Service {
    private Statut_Abonnement_Repository abonnementRepository;

    public Statut_Abonnement_ServiceImpl(Statut_Abonnement_Repository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }
}
