package com.car.rent_car.models;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;

    private String nom;

    @OneToMany(mappedBy = "marque")
    private List<Voiture> voitures;
    // Other fields, getters, setters, and constructors

}
