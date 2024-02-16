package com.car.rent_car.repository;

import com.car.rent_car.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Additional custom query methods can be defined here
}
