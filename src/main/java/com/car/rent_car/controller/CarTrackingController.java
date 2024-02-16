package com.car.rent_car.controller;

import com.car.rent_car.models.CarLocation;
import com.car.rent_car.requests.CarLocationRequest;
import com.car.rent_car.services.CarLocationService;
import com.car.rent_car.services.ILocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jps")
public class CarTrackingController {

    @Autowired
    private ILocation locationService;
    @Autowired
    private CarLocationService carLocationService;

    @PostMapping("/{voitureId}/locations")
    public ResponseEntity<CarLocation> createLocation(@PathVariable Long voitureId, @RequestBody CarLocationRequest locationRequest) {
        CarLocation location = locationService.createLocation(voitureId, locationRequest);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }
    @PostMapping("/car-locations")
    public ResponseEntity<CarLocation> createCarLocation(@RequestBody CarLocationRequest locationRequest) {
        CarLocation createdLocation = carLocationService.saveCarLocation(locationRequest);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarLocation>> getAllLocations() {
        List<CarLocation> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<CarLocation> getLocationById(@PathVariable Long id) {
        CarLocation location = locationService.getLocationById(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<CarLocation> updateLocation(@PathVariable Long id, @RequestBody CarLocationRequest locationRequest) {
        CarLocation updatedLocation = locationService.updateLocation(id, locationRequest);
        if (updatedLocation != null) {
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
