package com.car.rent_car.requests;

import com.car.rent_car.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponceRequest {

    private User user;
    private String jwt;


}
