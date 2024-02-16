package com.car.rent_car.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;






    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;

    // Other fields, getters, setters, and constructors

    // Constructors, getters, setters, and other methods
}
