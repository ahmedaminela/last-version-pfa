package com.car.rent_car.controller;


import com.car.rent_car.models.User;
import com.car.rent_car.requests.LoginResponceRequest;
import com.car.rent_car.requests.RegistrationRequest;
import com.car.rent_car.services.AutheticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AutheticationController {
    @Autowired
    private AutheticationService autheticationService;

        @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationRequest body){
        return autheticationService.RegisterUser(body.getUsername(), body.getPassword());
    }
    @PostMapping("/login")
    public LoginResponceRequest loginuser(@RequestBody RegistrationRequest body){
            return autheticationService.loginUser(body.getUsername(), body.getPassword());


    }
}
