package com.citybus.City.Bus.Project.repositories;

import com.citybus.City.Bus.Project.domain.entities.LigneEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneRepository extends CrudRepository<LigneEntity, Integer> {
    @Query("SELECT l.id FROM LigneEntity l WHERE l.nom_ligne = :nomLigne")
    Integer findIdByNomLigne(@Param("nomLigne") String nomLigne);
}
