package com.car.rent_car.requests;

import java.util.Date;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class AssuranceRequest {
    private String type;
    private Date date_fin;
    private Double prix_assurance;
    private long voiture_id;
}
