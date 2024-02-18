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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000") // Allows requests from your React app
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Adjust as needed
						.allowedHeaders("*")
						.allowCredentials(true); // If your frontend needs to send cookies or use authentication
			}

		};
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
