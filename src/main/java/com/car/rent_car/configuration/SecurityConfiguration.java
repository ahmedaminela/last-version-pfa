package com.car.rent_car.configuration;


import com.car.rent_car.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    private final RSAKeyProperties keys;
    public SecurityConfiguration(RSAKeyProperties keys){
        this.keys = keys;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        encoderMap.put(idForEncode, new BCryptPasswordEncoder());       // Add other encoders if needed

        return new DelegatingPasswordEncoder(idForEncode, encoderMap);
    }
    @Bean
    public AuthenticationManager authManager (UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        return new ProviderManager(daoProvider);
    }
    @Bean
   public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

         http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(auth ->{
                   auth.requestMatchers("/auth/**").permitAll();
                   auth.requestMatchers("/admin/**").hasRole("ADMIN");
                   auth.requestMatchers("/user/**").hasAnyRole("ADMIN","USER");
                   auth.anyRequest().authenticated();
               });
         http
               .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
         http
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
              return http.build();

   }

   @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();

   }
   @Bean
    public JwtEncoder jwtEncoder(){
       JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
       JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
       return  new NimbusJwtEncoder(jwks);
   }
   @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
       JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
       jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
       jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
       JwtAuthenticationConverter jwtconverter = new JwtAuthenticationConverter();
       jwtconverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
       return jwtconverter;



   }

}