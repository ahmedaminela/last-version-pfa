package com.car.rent_car.requests;

import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ReservationRequest {
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Long clientId;
    private Long voitureId;
}
