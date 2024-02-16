package com.car.rent_car.controller;


import com.car.rent_car.models.Voiture;
import com.car.rent_car.requests.VoitureRequest;
import com.car.rent_car.services.VoitureService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    private final VoitureService voitureService;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    // Endpoint to get all voitures
    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.getAllVoitures();
        return new ResponseEntity<>(voitures, HttpStatus.OK);
    }

    // Endpoint to get a voiture by ID
    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        Optional<Voiture> voiture = Optional.ofNullable(voitureService.getVoitureById(id));
        return voiture.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new voiture
    @PostMapping
    public ResponseEntity<Voiture> createVoiture(@RequestBody VoitureRequest voitureRequest) {
        Voiture createdVoiture = voitureService.saveVoiture(voitureRequest);
        return new ResponseEntity<>(createdVoiture, HttpStatus.CREATED);
    }

    // Endpoint to update an existing voiture
    @PutMapping("/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id, @RequestBody VoitureRequest voitureRequest) {
        Voiture updatedVoiture = voitureService.updateVoiture(id, voitureRequest);
        return new ResponseEntity<>(updatedVoiture, HttpStatus.CREATED);
    }

    // Endpoint to delete a voiture by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        Optional<Voiture> existingVoiture = Optional.ofNullable(voitureService.getVoitureById(id));
        if (existingVoiture.isPresent()) {
            voitureService.deleteVoiture(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
