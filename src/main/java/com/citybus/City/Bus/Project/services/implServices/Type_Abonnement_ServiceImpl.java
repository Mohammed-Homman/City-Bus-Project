package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.Type_Abonnement_Repository;
import com.citybus.City.Bus.Project.services.Type_Abonnement_Service;

public class Type_Abonnement_ServiceImpl implements Type_Abonnement_Service {
    private Type_Abonnement_Repository abonnementRepository;

    public Type_Abonnement_ServiceImpl(Type_Abonnement_Repository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }
}
