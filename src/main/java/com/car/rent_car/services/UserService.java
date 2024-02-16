package com.car.rent_car.services;

import com.car.rent_car.models.Role;
import com.car.rent_car.models.User;
import com.car.rent_car.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user is not valid"));

    }
}
