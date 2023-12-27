package com.example.finalproject.controllers;

import com.example.finalproject.models.Food;
import com.example.finalproject.models.FoodCategory;
import com.example.finalproject.repositories.FoodCategoryRepository;
import com.example.finalproject.repositories.FoodRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @GetMapping("")
    private String index(Model model)
    {
        model.addAttribute("foods", foodRepository.findAll());
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "food/index";
    }

    @GetMapping("/new")
    public String showAddForm(Food food, Model model) {
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "food/new";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        model.addAttribute("food", food);
        model.addAttribute("categories", foodCategoryRepository.findAll());
        return "food/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        foodRepository.delete(food);
        model.addAttribute("foods", foodRepository.findAll());
        return "food/index";
    }

    @PostMapping("/addfood")
    public String add(@Valid Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "food/new";
        }
        foodRepository.save(food);
        model.addAttribute("foods", foodRepository.findAll());
        return "food/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            food.setId(id);
            return "food/edit";
        }
        foodRepository.save(food);
        model.addAttribute("foods", foodRepository.findAll());
        return "food/index";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam("name") String name, Model model) {
//        List<FoodCategory> foodCategories= foodCategoryRepository.findAll();
//        List<FoodCategory> sortGames = new ArrayList<>();
//        for (FoodCategory foodCategory:
//                foodCategories) {
//            if(foodCategory.getCategoryName().toLowerCase().contains(name.toLowerCase()))
//            {
//                sortGames.add(foodCategory);
//            }
//        }
//        model.addAttribute("categories", sortGames);
//        return "foodcategory/index";
//    }
}
