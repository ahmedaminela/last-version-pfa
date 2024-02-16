package com.car.rent_car.repository;

import com.car.rent_car.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
