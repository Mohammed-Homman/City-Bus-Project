package com.citybus.City.Bus.Project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbonnementDto {
    private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double prix;
}
