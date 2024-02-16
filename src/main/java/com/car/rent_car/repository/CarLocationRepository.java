package com.car.rent_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.car.rent_car.models.CarLocation;

public interface CarLocationRepository extends JpaRepository<CarLocation, Long> {
    // Find the latest location by voiture's ID
    //CarLocation findTopByVoitureIdOrderByTimestampDesc(Long voitureId);
     CarLocation save(CarLocation newLocation);

}
