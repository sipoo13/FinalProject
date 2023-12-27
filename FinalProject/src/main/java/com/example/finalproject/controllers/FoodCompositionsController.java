package com.example.finalproject.controllers;

import com.example.finalproject.models.Allergen;
import com.example.finalproject.models.Composition;
import com.example.finalproject.models.Food;
import com.example.finalproject.repositories.AllergenRepository;
import com.example.finalproject.repositories.CompositionRepository;
import com.example.finalproject.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/food_compositions")
public class FoodCompositionsController {
    @Autowired
    private CompositionRepository compositionRepository;

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("")
    private String index(Model model) {
        model.addAttribute("foods", foodRepository.findAll());
        return "foodcomposition/index";
    }

    @GetMapping("/new")
    private String showAddform(Composition composition, Food food, Model model) {
        model.addAttribute("compositions", compositionRepository.findAll());
        model.addAttribute("foods", foodRepository.findAll());
        return "foodcomposition/new";
    }

    @GetMapping("/del")
    private String showDelform(Composition composition, Food food, Model model) {
        model.addAttribute("compositions", compositionRepository.findAll());
        model.addAttribute("foods", foodRepository.findAll());
        return "foodcomposition/remove";
    }

    @PostMapping("/addcompositions")
    public String productPostAdd(@RequestParam Long composition, @RequestParam Long food, Model model)
    {
        Food foundFood = foodRepository.findById(food).orElseThrow();
        Composition foundComposition = compositionRepository.findById(composition).orElseThrow();
        foundFood.getCompositions().add(foundComposition);
        foodRepository.save(foundFood);
        return "redirect:/food_compositions";
    }

    @PostMapping("/removecompositions")
    public String productPostRemove(@RequestParam Long composition, @RequestParam Long food, Model model) {
        Food foundFood = foodRepository.findById(food).orElseThrow();
        Composition foundComposition = compositionRepository.findById(composition).orElseThrow();
        foundFood.getCompositions().remove(foundComposition);
        foodRepository.save(foundFood);
        return "redirect:/food_compositions";
    }
}
