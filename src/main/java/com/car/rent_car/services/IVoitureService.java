package com.car.rent_car.services;

import com.car.rent_car.models.Voiture;
import com.car.rent_car.requests.VoitureRequest;

import java.util.List;

public interface IVoitureService {
    List<Voiture> getAllVoitures();

    Voiture getVoitureById(Long id);

    Voiture saveVoiture(VoitureRequest voitureRequest);

    Voiture updateVoiture(Long id, VoitureRequest voitureRequest);

    void deleteVoiture(Long id);
}
