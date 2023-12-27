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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @GetMapping("/new")
    public String showAddForm(FastFoodUser user, Model model) {
        model.addAttribute("user", new FastFoodUser());
        return "user/new";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        FastFoodUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        model.addAttribute("user", user);
        return "user/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        FastFoodUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @PostMapping("/adduser")
    public String addPerson(@Valid FastFoodUser user, BindingResult result, @RequestParam("roles") String role, Model model) {
        if (result.hasErrors()) {
            return "user/new";
        }
        Role selectedRole = Role.valueOf(role);
        Set<Role> roles = new HashSet<>();
        roles.add(selectedRole);
        user.setRoles(roles);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid FastFoodUser user, BindingResult result, @RequestParam("roles") String role, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user/edit";
        }
        Role selectedRole = Role.valueOf(role);
        Set<Role> roles = new HashSet<>();
        roles.add(selectedRole);
        user.setRoles(roles);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }
}
