package com.car.rent_car.repository;

import com.car.rent_car.models.PrixVoiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrixVoitureRepository extends JpaRepository<PrixVoiture, Long> {
    // Additional custom query methods can be defined here
}
