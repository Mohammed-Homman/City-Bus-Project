package com.citybus.City.Bus.Project.domain.entities;

import com.citybus.City.Bus.Project.domain.JourSemaine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Horaire")
public class HoraireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private JourSemaine jour_semaine;

    @ElementCollection
    private List<LocalTime> heure;
}
