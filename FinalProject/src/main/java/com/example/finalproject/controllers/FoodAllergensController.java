package com.example.finalproject.controllers;

import com.example.finalproject.models.Allergen;
import com.example.finalproject.models.Food;
import com.example.finalproject.repositories.AllergenRepository;
import com.example.finalproject.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/food_allergens")
public class FoodAllergensController {
    @Autowired
    private AllergenRepository allergenRepository;

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("")
    private String index(Model model) {
        model.addAttribute("foods", foodRepository.findAll());
        return "foodallergen/index";
    }

    @GetMapping("/new")
    private String showAddform(Allergen allergen, Food food, Model model) {
        model.addAttribute("allergens", allergenRepository.findAll());
        model.addAttribute("foods", foodRepository.findAll());
        return "foodallergen/new";
    }

    @GetMapping("/del")
    private String showDelform(Allergen allergen, Food food, Model model) {
        model.addAttribute("allergens", allergenRepository.findAll());
        model.addAttribute("foods", foodRepository.findAll());
        return "foodallergen/remove";
    }

    @PostMapping("/addallergens")
    public String productPostAdd(@RequestParam Long allergen, @RequestParam Long food, Model model)
    {
        Food foundFood = foodRepository.findById(food).orElseThrow();
        Allergen foundAllergen = allergenRepository.findById(allergen).orElseThrow();
        foundFood.getAllergens().add(foundAllergen);
        foodRepository.save(foundFood);
        return "redirect:/food_allergens";
    }

    @PostMapping("/removeallergens")
    public String productPostRemove(@RequestParam Long allergen, @RequestParam Long food, Model model) {
        Food foundFood = foodRepository.findById(food).orElseThrow();
        Allergen foundAllergen = allergenRepository.findById(allergen).orElseThrow();
        foundFood.getAllergens().remove(foundAllergen);
        foodRepository.save(foundFood);
        return "redirect:/food_allergens";
    }
}
