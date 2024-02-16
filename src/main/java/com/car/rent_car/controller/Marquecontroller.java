package com.car.rent_car.controller;

import com.car.rent_car.models.Marque;
import com.car.rent_car.requests.MarqueRequest;
import com.car.rent_car.services.MarqueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marques")
public class Marquecontroller {
    private final MarqueService marqueService;

    @Autowired
    public Marquecontroller(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    // Endpoint to get all marques
    @GetMapping
    public ResponseEntity<List<Marque>> getAllMarques() {
        List<Marque> marques = marqueService.getAllMarque();
        return new ResponseEntity<>(marques, HttpStatus.OK);
    }

    // Endpoint to get a marque by ID
    @GetMapping("/{id}")
    public ResponseEntity<Marque> getMarqueById(@PathVariable Long id) {
        Marque marque = marqueService.findMarqueById(id);
        if (marque != null) {
            return new ResponseEntity<>(marque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create a new marque
    @PostMapping
    public ResponseEntity<Marque> createMarque(@RequestBody MarqueRequest marqueRequest) {
        Marque newMarque = new Marque();
        BeanUtils.copyProperties(marqueRequest,newMarque);
        Marque createdMarque = marqueService.saveMarque(newMarque);
        return new ResponseEntity<>(createdMarque, HttpStatus.CREATED);
    }

    // Endpoint to delete a marque by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable Long id) {
        marqueService.deleteMarqueById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
