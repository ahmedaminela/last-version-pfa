package com.car.rent_car.services;

import com.car.rent_car.models.PrixVoiture;
import com.car.rent_car.requests.PrixVoitureRequest;

import java.util.List;

public interface IPrixvoitureService {

    PrixVoiture createPrixVoiture(PrixVoitureRequest prixVoitureRequest);

    List<PrixVoiture> getAllPrixVoitures();

    PrixVoiture getPrixVoitureById(Long id);

    PrixVoiture updatePrixVoiture(Long id, PrixVoitureRequest prixVoitureRequest);

    void deletePrixVoiture(Long id);
}
