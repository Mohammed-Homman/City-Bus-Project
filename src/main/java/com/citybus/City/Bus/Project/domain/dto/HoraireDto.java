package com.citybus.City.Bus.Project.domain.dto;

import com.citybus.City.Bus.Project.domain.JourSemaine;
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
public class HoraireDto {
    private int id;
    private JourSemaine jour_semaine;
    private List<LocalTime> heure;
}
