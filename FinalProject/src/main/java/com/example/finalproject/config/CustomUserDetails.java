package com.example.finalproject.config;

import com.example.finalproject.models.FastFoodUser;
import com.example.finalproject.models.Role;
import com.example.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FastFoodUser user = userRepository.findFastFoodUserByLogin(username);
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .roles(user.getRoles().stream().map(Enum::name).toArray(String[]::new))
                        .build();
        return userDetails;
    }
}
