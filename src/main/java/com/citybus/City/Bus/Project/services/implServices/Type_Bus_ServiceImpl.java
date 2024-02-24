package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.repositories.Type_Abonnement_Repository;
import com.citybus.City.Bus.Project.services.Type_Bus_Service;

public class Type_Bus_ServiceImpl implements Type_Bus_Service {
    private Type_Abonnement_Repository abonnementRepository;

    public Type_Bus_ServiceImpl(Type_Abonnement_Repository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }
}
