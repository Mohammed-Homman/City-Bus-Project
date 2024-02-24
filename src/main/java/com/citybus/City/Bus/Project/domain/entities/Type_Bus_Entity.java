package com.citybus.City.Bus.Project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Type_Bus")

public class Type_Bus_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int type_bus_id;
    private String nom_type_bus;


}
