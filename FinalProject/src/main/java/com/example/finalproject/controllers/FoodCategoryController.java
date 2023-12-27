package com.example.finalproject.controllers;

import com.example.finalproject.models.Food;
import com.example.finalproject.models.FoodCategory;
import com.example.finalproject.repositories.FoodCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class FoodCategoryController {
    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @GetMapping("")
    private String index(Model model)
    {
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "foodcategory/index";
    }

    @GetMapping("/new")
    public String showAddForm(FoodCategory foodCategory) {
        return "foodcategory/new";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        FoodCategory foodCategory = foodCategoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        model.addAttribute("foodCategory", foodCategory);
        return "foodcategory/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        FoodCategory foodCategory = foodCategoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        foodCategoryRepository.delete(foodCategory);
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "foodcategory/index";
    }

    @PostMapping("/addcategory")
    public String add(@Valid FoodCategory foodCategory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "foodcategory/new";
        }
        foodCategoryRepository.save(foodCategory);
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "foodcategory/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid FoodCategory foodCategory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            foodCategory.setId(id);
            return "foodcategory/edit";
        }
        foodCategoryRepository.save(foodCategory);
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "foodcategory/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<FoodCategory> foodCategories= foodCategoryRepository.findAll();
        List<FoodCategory> sortGames = new ArrayList<>();
        for (FoodCategory foodCategory:
                foodCategories) {
            if(foodCategory.getCategoryName().toLowerCase().contains(name.toLowerCase()))
            {
                sortGames.add(foodCategory);
            }
        }
        model.addAttribute("categories", sortGames);
        return "foodcategory/index";
    }
}
