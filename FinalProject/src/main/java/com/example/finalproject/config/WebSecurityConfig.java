package com.example.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomUserDetails customUserDetails;

    @Bean
    public AuthenticationManager authenticationManager
            (HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetails).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/registration").permitAll()
                        .requestMatchers("/users").hasRole("ADMIN")
                        .requestMatchers("/roles").hasRole("MANAGER")
                        .requestMatchers("/foods").hasRole("ADMIN")
                        .requestMatchers("/allergens").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers("/compositions").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers("/stocks").hasRole("ADMIN")
                        .requestMatchers("/food_compositions").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers("/food_allergens").hasAnyRole("ADMIN","EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
