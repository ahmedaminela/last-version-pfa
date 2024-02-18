package com.car.rent_car.requests;

import com.car.rent_car.models.Marque;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
public class VoitureRequest {

    private String modele;
    private String matricule;
    private String couleur;
    private Boolean dispo;
    private String carburant;
    private Double km;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+1")
    private Date date_sortie;
    private int puissance;
    private String img;
    private long marque_id;
}
