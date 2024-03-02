package com.citybus.City.Bus.Project.services.implServices;

import com.citybus.City.Bus.Project.domain.entities.HoraireEntity;
import com.citybus.City.Bus.Project.repositories.HoraireRepository;
import com.citybus.City.Bus.Project.services.HoraireService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HoraireServiceImpl implements HoraireService {
    private HoraireRepository horaireRepository;

    public HoraireServiceImpl(HoraireRepository horaireRepository) {
        this.horaireRepository = horaireRepository;
    }

    @Override
    public HoraireEntity save(HoraireEntity horaireEntity) {
        return horaireRepository.save(horaireEntity);
    }

    @Override
    public List<HoraireEntity> findAll() {
        return StreamSupport.stream(horaireRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<HoraireEntity> findOne(int id) {
        return horaireRepository.findById(id);
    }

    @Override
    public boolean isExists(int id) {
        return horaireRepository.existsById(id);
    }

    @Override
    public HoraireEntity partialUpdate(int id, HoraireEntity horaireEntity) {
        horaireEntity.setId(id);

        return horaireRepository.findById(id).map(existingHoraire -> {
            // Update fields if present in the provided entity
            Optional.ofNullable(horaireEntity.getJour_semaine()).ifPresent(existingHoraire::setJour_semaine);
            Optional.ofNullable(horaireEntity.getHeure()).ifPresent(existingHoraire::setHeure);

            // Save and return the updated entity
            return horaireRepository.save(existingHoraire);
        }).orElseThrow(() -> new RuntimeException("Horaire does not exist"));
    }


    @Override
    public void delete(int id) {
        horaireRepository.deleteById(id);
    }
}
