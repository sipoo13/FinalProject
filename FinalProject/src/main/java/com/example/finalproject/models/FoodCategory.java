package com.example.finalproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Entity
@Table(name = "food_category")
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Length(min = 3,message = "Название должно содержать минимум 3 символа")
    private String categoryName;
    @OneToMany(mappedBy = "foodCategory")
    private List<Food> foodItems;

    public FoodCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<Food> foodItems) {
        this.foodItems = foodItems;
    }
}
