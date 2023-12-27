package com.example.finalproject.controllers;

import com.example.finalproject.models.Composition;
import com.example.finalproject.models.FoodCategory;
import com.example.finalproject.repositories.CompositionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/compositions")
public class CompositionController {
    @Autowired
    private CompositionRepository compositionRepository;

    @GetMapping("")
    private String index(Model model)
    {
        model.addAttribute("compositions", compositionRepository.findAll());
        return "composition/index";
    }

    @GetMapping("/new")
    public String showAddForm(Composition composition) {
        return "composition/new";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Composition composition = compositionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        model.addAttribute("composition", composition);
        return "composition/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Composition composition = compositionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        compositionRepository.delete(composition);
        model.addAttribute("compositions", compositionRepository.findAll());
        return "composition/index";
    }

    @PostMapping("/addcomposition")
    public String add(@Valid Composition composition, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "composition/new";
        }
        compositionRepository.save(composition);
        model.addAttribute("compositions", compositionRepository.findAll());
        return "composition/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Composition composition, BindingResult result, Model model) {
        if (result.hasErrors()) {
            composition.setId(id);
            return "composition/edit";
        }
        compositionRepository.save(composition);
        model.addAttribute("compositions", compositionRepository.findAll());
        return "composition/index";
    }
}
