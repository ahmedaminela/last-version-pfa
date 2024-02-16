package com.car.rent_car.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationRequest {
    private  String username;
    private  String password;

}
