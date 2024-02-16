package com.car.rent_car.services;

import com.car.rent_car.models.Role;
import com.car.rent_car.models.User;
import com.car.rent_car.repository.RoleRepository;
import com.car.rent_car.repository.UserRepository;
import com.car.rent_car.requests.LoginResponceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AutheticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public User RegisterUser(String username,String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new User(0,username,encodedPassword,authorities));
    }
    public LoginResponceRequest loginUser(String username , String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);
            return  new LoginResponceRequest(userRepository.findByUsername(username).get(),token);


        }
        catch(AuthenticationException e){
            return new LoginResponceRequest(null,"");

        }


    }




}
