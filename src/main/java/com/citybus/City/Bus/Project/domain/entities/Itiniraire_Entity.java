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
@Table(name = "Itiniraire")
public class Itiniraire_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "itiniraire_id_seq")
    private int itiniraire_id;
}
