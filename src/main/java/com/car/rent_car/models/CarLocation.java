package com.car.rent_car.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CarLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    @JsonIgnore
    @OneToOne(mappedBy = "carLocation", fetch = FetchType.LAZY)
    private Voiture voiture;

    // Constructors, getters, and setters
}
