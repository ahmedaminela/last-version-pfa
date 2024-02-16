package com.car.rent_car.services;

import com.car.rent_car.models.CarLocation;
import com.car.rent_car.requests.CarLocationRequest;

import java.util.List;

public interface ILocation {

    CarLocation createLocation(Long voitureId,CarLocationRequest locationRequest);

    List<CarLocation> getAllLocations();

    CarLocation getLocationById(Long id);

    CarLocation updateLocation(Long id, CarLocationRequest locationRequest);

    void deleteLocation(Long id);
}
