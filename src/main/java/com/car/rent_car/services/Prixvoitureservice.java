package com.car.rent_car.services;

import com.car.rent_car.models.PrixVoiture;
import com.car.rent_car.models.Voiture;
import com.car.rent_car.repository.PrixVoitureRepository;
import com.car.rent_car.requests.PrixVoitureRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Prixvoitureservice implements IPrixvoitureService {

    @Autowired
    private PrixVoitureRepository prixVoitureRepository;
    @Autowired
    VoitureService voitureService;

    @Override
    public PrixVoiture createPrixVoiture(PrixVoitureRequest prixVoitureRequest) {
        PrixVoiture prixVoiture = new PrixVoiture();
        Voiture voiture = voitureService.getVoitureById(prixVoitureRequest.getVoiture_id());

        BeanUtils.copyProperties(prixVoitureRequest, prixVoiture);
        prixVoiture.setVoiture(voiture);

        return prixVoitureRepository.save(prixVoiture);
    }

    @Override
    public List<PrixVoiture> getAllPrixVoitures() {
        return prixVoitureRepository.findAll();
    }

    @Override
    public PrixVoiture getPrixVoitureById(Long id) {
        return prixVoitureRepository.findById(id).orElse(null);
    }

    @Override
    public PrixVoiture updatePrixVoiture(Long id, PrixVoitureRequest prixVoitureRequest) {
        PrixVoiture existingPrixVoiture = prixVoitureRepository.findById(id).orElse(null);
        if (existingPrixVoiture != null) {
            BeanUtils.copyProperties(prixVoitureRequest, existingPrixVoiture);
            return prixVoitureRepository.save(existingPrixVoiture);
        }
        return null;
    }

    @Override
    public void deletePrixVoiture(Long id) {
        prixVoitureRepository.deleteById(id);
    }
}
