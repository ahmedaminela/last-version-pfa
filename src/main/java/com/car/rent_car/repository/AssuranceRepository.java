package com.car.rent_car.repository;

import com.car.rent_car.models.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    // Additional custom query methods can be defined here
}
