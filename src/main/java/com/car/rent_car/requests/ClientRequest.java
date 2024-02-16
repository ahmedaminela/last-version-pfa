package com.car.rent_car.requests;


import com.car.rent_car.models.Reservation;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ClientRequest {








    private String nom;
    private String prenom;
    private String CNI;
    private String CNI_path;
    private String N_permis;
    private String sexe;
    private String Adresse;
    private String telephone;
    private String email;

}
