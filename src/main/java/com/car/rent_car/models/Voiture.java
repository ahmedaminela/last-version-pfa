package com.car.rent_car.models;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString

public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modele;
    private String matricule;
    private String couleur;
    private Boolean dispo;
    private String carburant;
    private Double km;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+1")
    private Date date_sortie;
    private int puissance;

    @OneToMany(mappedBy = "voiture")
    private List<Reservation> reservations;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "marque_id")
    @JsonIgnore
    private Marque marque;
    @OneToOne(mappedBy = "voiture")
    private Assurance assurance;
    @OneToMany(mappedBy = "voiture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrixVoiture> prixList;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carLocation_id")
    private CarLocation carLocation;

    // Consider adding utility methods to manage bi-directional relationships
    public void setCarLocation(CarLocation carLocation) {
        if (carLocation == null) {
            if (this.carLocation != null) {
                this.carLocation.setVoiture(null);
            }
        } else {
            carLocation.setVoiture(this);
        }
        this.carLocation = carLocation;
    }

    // Other fields, getters, setters, and constructors

}

