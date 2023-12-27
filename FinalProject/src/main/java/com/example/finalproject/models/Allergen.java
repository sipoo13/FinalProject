package com.example.finalproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "allergen")
public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String allergenName;

    @ManyToMany
    @JoinTable(name="food_allergens",
            joinColumns=@JoinColumn(name="allergen_id"),
            inverseJoinColumns=@JoinColumn(name="food_id"))
    private List<Food> foods;

    public Allergen() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAllergenName() {
        return allergenName;
    }

    public void setAllergenName(String allergenName) {
        this.allergenName = allergenName;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
