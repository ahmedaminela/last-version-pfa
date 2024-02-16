package com.car.rent_car.services;

import com.car.rent_car.models.Assurance;
import com.car.rent_car.models.Voiture;
import com.car.rent_car.repository.AssuranceRepository;
import com.car.rent_car.requests.AssuranceRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssuranceService {

    @Autowired
    private AssuranceRepository assuranceRepository;
    @Autowired
    VoitureService voitureService;

    public List<Assurance> getAllAssurances() {
        return assuranceRepository.findAll();
    }

    public Assurance getAssuranceById(Long id) {
        return assuranceRepository.findById(id).orElse(null);
    }

    public Assurance saveAssurance(AssuranceRequest assuranceRequest) {
        Assurance newAssurance = new Assurance();

        Voiture voiture = voitureService.getVoitureById(assuranceRequest.getVoiture_id());

        BeanUtils.copyProperties(assuranceRequest, newAssurance);
        newAssurance.setVoiture(voiture);

        return assuranceRepository.save(newAssurance);
    }
    public Assurance updateAssurance(Long id, Assurance newAssurance) {
        Optional<Assurance> oldAssuranceOptional = assuranceRepository.findById(id);
        if (oldAssuranceOptional.isPresent()) {
            Assurance oldAssurance = oldAssuranceOptional.get();
            oldAssurance.setType(newAssurance.getType());
            oldAssurance.setDate_fin(newAssurance.getDate_fin());
            oldAssurance.setPrix_assurance(newAssurance.getPrix_assurance());
            oldAssurance.setVoiture(newAssurance.getVoiture());
            return assuranceRepository.save(oldAssurance);
        }
        return null;
    }

    public void deleteAssurance(Long id) {
        assuranceRepository.deleteById(id);
    }
}
