package com.citybus.City.Bus.Project.domain.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Type_Bus_Dto {
    private int type_bus_id;
    private String nom_type_bus;

}
