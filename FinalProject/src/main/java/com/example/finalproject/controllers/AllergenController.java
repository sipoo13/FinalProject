package com.example.finalproject.controllers;

import com.example.finalproject.models.Allergen;
import com.example.finalproject.models.Composition;
import com.example.finalproject.repositories.AllergenRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allergens")
public class AllergenController {
    @Autowired
    private AllergenRepository allergenRepository;

    @GetMapping("")
    private String index(Model model)
    {
        model.addAttribute("allergens", allergenRepository.findAll());
        return "allergen/index";
    }

    @GetMapping("/new")
    public String showAddForm(Allergen allergen) {
        return "allergen/new";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Allergen allergen = allergenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        model.addAttribute("allergen", allergen);
        return "allergen/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Allergen allergen = allergenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        allergenRepository.delete(allergen);
        model.addAttribute("allergens", allergenRepository.findAll());
        return "allergen/index";
    }

    @PostMapping("/addallergen")
    public String add(@Valid Allergen allergen, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "allergen/new";
        }
        allergenRepository.save(allergen);
        model.addAttribute("allergens", allergenRepository.findAll());
        return "allergen/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Allergen allergen, BindingResult result, Model model) {
        if (result.hasErrors()) {
            allergen.setId(id);
            return "allergen/edit";
        }
        allergenRepository.save(allergen);
        model.addAttribute("allergens", allergenRepository.findAll());
        return "allergen/index";
    }
}
