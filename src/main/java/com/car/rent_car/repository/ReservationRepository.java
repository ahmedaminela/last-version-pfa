package com.car.rent_car.repository;

import com.car.rent_car.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Additional custom query methods can be defined here
}
