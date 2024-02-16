package com.car.rent_car.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// @Data // Consider the implications of using @Data with JPA entities
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;

    private String authority; // Renamed from 'Authority' to 'authority'

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
