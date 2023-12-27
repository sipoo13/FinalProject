package com.example.finalproject.controllers;

import com.example.finalproject.models.Stock;
import com.example.finalproject.repositories.FoodRepository;
import com.example.finalproject.repositories.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("")
    private String index(Model model) {
        model.addAttribute("stocks", stockRepository.findAll());
        return "stock/index";
    }

    @GetMapping("/new")
    public String showAddForm(Stock stock, Model model) {
        model.addAttribute("foods", foodRepository.findAll());
        return "stock/new";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        model.addAttribute("foods", foodRepository.findAll());
        model.addAttribute("stock", stock);
        return "stock/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid strawberry Id: " + id));
        stockRepository.delete(stock);
        model.addAttribute("stocks", stockRepository.findAll());
        return "stock/index";
    }

    @PostMapping("/addstock")
    public String add(@Valid Stock stock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "stock/new";
        }
        stockRepository.save(stock);
        model.addAttribute("stocks", stockRepository.findAll());
        return "stock/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Stock stock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            stock.setId(id);
            return "stock/edit";
        }
        stockRepository.save(stock);
        model.addAttribute("stocks", stockRepository.findAll());
        return "stock/index";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam("name") String name, Model model) {
//        List<Stock> stocks = stockRepository.findAll();
//        List<Stock> sortGames = new ArrayList<>();
//        for (FoodCategory foodCategory:
//                stocks) {
//            if(foodCategory.getCategoryName().toLowerCase().contains(name.toLowerCase()))
//            {
//                sortGames.add(foodCategory);
//            }
//        }
//        model.addAttribute("categories", sortGames);
//        return "foodcategory/index";
//    }
}
