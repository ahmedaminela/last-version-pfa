package com.car.rent_car.services;

import com.car.rent_car.models.Marque;
import com.car.rent_car.models.PrixVoiture;
import com.car.rent_car.models.Voiture;
import com.car.rent_car.repository.VoitureRepository;
import com.car.rent_car.requests.VoitureRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class VoitureService implements IVoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private MarqueService marqueService;

    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    public Voiture getVoitureById(Long id) {
        return voitureRepository.findById(id).orElse(null);
    }

    public Voiture saveVoiture(VoitureRequest voitureRequest) {
        Voiture newVoiture = new Voiture();

        Marque selectedMarque = marqueService.findMarqueById(voitureRequest.getMarque_id());
        BeanUtils.copyProperties(voitureRequest, newVoiture);
        newVoiture.setMarque(selectedMarque);


        return voitureRepository.save(newVoiture);
    }

    public Voiture updateVoiture(Long id, VoitureRequest voitureRequest) {
        Voiture oldVoiture = voitureRepository.findById(id).get();
        Marque selectedMarque = marqueService.findMarqueById(voitureRequest.getMarque_id());
        BeanUtils.copyProperties(voitureRequest, oldVoiture);
        oldVoiture.setMarque(selectedMarque);

        return voitureRepository.save(oldVoiture);
    }


    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }


}
