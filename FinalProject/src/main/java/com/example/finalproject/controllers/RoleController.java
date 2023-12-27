package com.example.finalproject.controllers;

import com.example.finalproject.models.FastFoodUser;
import com.example.finalproject.models.Role;
import com.example.finalproject.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "role/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        FastFoodUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "role/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid FastFoodUser user,
                         @RequestParam(name = "roles[]", required = false) String[] roles, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "role/edit";
        }
        user = userRepository.findFastFoodUserByLogin(user.getLogin());
        user.getRoles().clear();
        if (roles != null) {
            for (String role : roles) {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "role/index";
    }
}
