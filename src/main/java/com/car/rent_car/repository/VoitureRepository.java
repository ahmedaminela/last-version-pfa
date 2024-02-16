package com.car.rent_car.repository;

import com.car.rent_car.models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    // Additional custom query methods can be defined here
}
