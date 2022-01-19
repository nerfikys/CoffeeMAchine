package com.example.Spring.domain;

import java.util.ArrayList;

public class CoffeeType { //Рецепты кофе
    private String name;
    private int coffee;
    private int milk;
    private int cream;
    private int water;

    public CoffeeType(String name, int coffee, int milk, int cream, int water) {
        this.name = name;
        this.coffee = coffee;
        this.milk = milk;
        this.cream = cream;
        this.water = water;
    }

    public String getName() {
        return name;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getMilk() {
        return milk;
    }

    public int getCream() {
        return cream;
    }

    public int getWater() {
        return water;
    }
}
