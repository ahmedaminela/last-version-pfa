package com.car.rent_car.controllers;

import com.car.rent_car.models.PrixVoiture;
import com.car.rent_car.requests.PrixVoitureRequest;
import com.car.rent_car.services.IPrixvoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prixvoitures")
public class Prixvoiturecontroller {

    @Autowired
    private IPrixvoitureService prixVoitureService;

    @PostMapping
    public ResponseEntity<PrixVoiture> createPrixVoiture(@RequestBody PrixVoitureRequest prixVoitureRequest) {
        PrixVoiture prixVoiture = prixVoitureService.createPrixVoiture(prixVoitureRequest);
        return new ResponseEntity<>(prixVoiture, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PrixVoiture>> getAllPrixVoitures() {
        List<PrixVoiture> prixVoitures = prixVoitureService.getAllPrixVoitures();
        return new ResponseEntity<>(prixVoitures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrixVoiture> getPrixVoitureById(@PathVariable Long id) {
        PrixVoiture prixVoiture = prixVoitureService.getPrixVoitureById(id);
        if (prixVoiture != null) {
            return new ResponseEntity<>(prixVoiture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrixVoiture> updatePrixVoiture(@PathVariable Long id, @RequestBody PrixVoitureRequest prixVoitureRequest) {
        PrixVoiture updatedPrixVoiture = prixVoitureService.updatePrixVoiture(id, prixVoitureRequest);
        if (updatedPrixVoiture != null) {
            return new ResponseEntity<>(updatedPrixVoiture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrixVoiture(@PathVariable Long id) {
        prixVoitureService.deletePrixVoiture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
