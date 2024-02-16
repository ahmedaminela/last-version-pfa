package com.car.rent_car.requests;

import com.car.rent_car.enums.PricingScenario;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class PrixVoitureRequest {

    private BigDecimal prix;

    private PricingScenario scenario;
    private Long voiture_id;

    // Constructors, getters, and setters
}
