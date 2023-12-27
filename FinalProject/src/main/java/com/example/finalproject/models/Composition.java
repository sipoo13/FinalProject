package com.example.finalproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "compostion")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String compositionName;
    @ManyToMany
    @JoinTable(name="food_composition",
            joinColumns=@JoinColumn(name="composition_id"),
            inverseJoinColumns=@JoinColumn(name="food_id"))
    private List<Food> foods;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompositionName() {
        return compositionName;
    }

    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
