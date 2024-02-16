package com.car.rent_car.services;

import com.car.rent_car.models.Reservation;
import com.car.rent_car.requests.ReservationRequest;

import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);

    Reservation createReservation(ReservationRequest requestReservation);

    Reservation updateReservation(Long id, ReservationRequest reservationRequest);

    void deleteReservation(Long id);
}
