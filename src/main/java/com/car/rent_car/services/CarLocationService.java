package com.car.rent_car.services;

import com.car.rent_car.models.CarLocation;
import com.car.rent_car.models.Voiture;
import com.car.rent_car.repository.CarLocationRepository;
import com.car.rent_car.repository.VoitureRepository;
import com.car.rent_car.requests.CarLocationRequest;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarLocationService implements ILocation {

    private final CarLocationRepository locationRepository;
    private final VoitureRepository voitureRepository;

    @Autowired
    VoitureService voitureService;
    

    @Autowired
    public CarLocationService(CarLocationRepository locationRepository, VoitureRepository voitureRepository) {
        this.locationRepository = locationRepository;
        this.voitureRepository = voitureRepository;
    }

    public CarLocation saveCarLocation(CarLocationRequest locationRequest) {
        CarLocation newLocation = new CarLocation();

        Voiture voiture = voitureService.getVoitureById(locationRequest.getVoiture_id());

        // Assuming locationRequest contains latitude and longitude properties
        newLocation.setLatitude(locationRequest.getLatitude());
        newLocation.setLongitude(locationRequest.getLongitude());

        // Set the associated Voiture
        newLocation.setVoiture(voiture);

        // Save the CarLocation

        return locationRepository.save(newLocation);
    }

    @Override
    public CarLocation createLocation(Long voitureId, CarLocationRequest locationRequest) {
        // Fetch the corresponding Voiture entity using voitureId
        Voiture voiture = voitureRepository.findById(voitureId)
                .orElseThrow(() -> new EntityNotFoundException("Voiture with id " + voitureId + " not found"));

        // Create a new CarLocation entity
        CarLocation location = new CarLocation();
        BeanUtils.copyProperties(locationRequest, location);
        location.setVoiture(voiture);

        // Save the CarLocation entity
        return locationRepository.save(location);
    }

    @Override
    public List<CarLocation> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public CarLocation getLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public CarLocation updateLocation(Long id, CarLocationRequest locationRequest) {
        CarLocation location = locationRepository.findById(id).orElse(null);
        if (location != null) {
            BeanUtils.copyProperties(locationRequest, location);
            return locationRepository.save(location);
        }
        return null; // Or throw exception if not found
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
