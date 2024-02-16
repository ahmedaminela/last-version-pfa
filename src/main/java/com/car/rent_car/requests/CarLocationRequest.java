package com.car.rent_car.requests;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CarLocationRequest {
    private double latitude;
    private double longitude;
    private Long voiture_id;



    // Constructors, getters, and setters
}
