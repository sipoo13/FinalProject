package com.example.finalproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Min(value = 1, message = "Скидка должна составлять от 1 до 50 процентов")
    @Max(value = 50, message = "Скидка должна составлять от 1 до 50 процентов")
    private int discount;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
    private String endDate;

    public Stock() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
