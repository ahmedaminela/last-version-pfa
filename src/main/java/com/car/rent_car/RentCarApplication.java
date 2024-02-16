package com.car.rent_car;

import com.car.rent_car.models.Role;
import com.car.rent_car.models.User;
import com.car.rent_car.repository.RoleRepository;
import com.car.rent_car.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role(1,"ADMIN"));
			roleRepository.save(new Role(2,"USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			User admin = new User(1L,"admin",passwordEncoder.encode("password"),roles);
	userRepository.save(admin);
		};
	}

}
