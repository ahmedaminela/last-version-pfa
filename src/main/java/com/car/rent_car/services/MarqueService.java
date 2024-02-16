package com.car.rent_car.services;

import com.car.rent_car.models.Marque;
import com.car.rent_car.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarqueService implements IMarque{
    @Autowired
    private  MarqueRepository marqueRepository;


    public List<Marque> getAllMarque() {
        return marqueRepository.findAll();
    }
    // Method to find a marque by ID
    public Marque findMarqueById(Long id) {
        return marqueRepository.findById(id).orElse(null);
    }


    public Marque saveMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    // Method to delete a marque by ID
    public void deleteMarqueById(Long id) {
        marqueRepository.deleteById(id);
    }
}
