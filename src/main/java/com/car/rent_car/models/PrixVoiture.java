package com.car.rent_car.models;

import com.car.rent_car.enums.PricingScenario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class PrixVoiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal prix;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;
    @Enumerated(EnumType.STRING)
    private PricingScenario scenario;

    // Other fields, getters, setters, and constructors

}
