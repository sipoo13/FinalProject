package com.example.finalproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String foodName;
    private String foodDescription;
    private String foodPrice;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    @ManyToMany
    @JoinTable(name="food_composition",
            joinColumns=@JoinColumn(name="food_id"),
            inverseJoinColumns=@JoinColumn(name="composition_id"))
    private List<Composition> compositions;

    @ManyToMany
    @JoinTable(name="food_allergens",
            joinColumns=@JoinColumn(name="food_id"),
            inverseJoinColumns=@JoinColumn(name="allergen_id"))
    private List<Allergen> allergens;

    @OneToMany(mappedBy = "food")
    private List<Stock> stockItems;

    public Food() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public List<Stock> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<Stock> stockItems) {
        this.stockItems = stockItems;
    }
}
