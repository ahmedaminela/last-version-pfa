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

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;
    private String CNI;
    private String CNI_path;
    private String N_permis;
    private String sexe;
    private String Adresse;
    private String telephone;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;


    // Other fields, getters, setters, and constructors

}
