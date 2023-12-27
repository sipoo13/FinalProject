package com.example.finalproject.controllers;

import com.example.finalproject.models.FastFoodUser;
import com.example.finalproject.models.Role;
import com.example.finalproject.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {return "index";}

    @GetMapping("/registration")
    public String regForm()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@Valid FastFoodUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.EMPLOYEE);
        user.setRoles(roles);

        userRepository.save(user);
        return "redirect:login";
    }
}
