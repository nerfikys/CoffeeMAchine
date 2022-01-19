package com.example.Spring.domain;

public class Capacity { // Хранилище кофемашины
    private int milk; // Молоко
    private int coffeeB;// Зерновой кофе
    private int coffeeG;// Молотый кофе
    private int water;// Вода
    private int sugar;// Сахар
    private int cream;// Сливки
/*
Констурктор,
гетеры и сетеры,
add" для наполнения хранилища(имееется максимум и добавляется к текущему),
PrintInfo вывод информации о наполнености в консоль
 */
    public Capacity(int milk, int coffeeB, int coffeeG, int water, int sugar, int cream) {
        this.milk = milk;
        this.coffeeB = coffeeB;
        this.coffeeG = coffeeG;
        this.water = water;
        this.sugar = sugar;
        this.cream = cream;
    }

    public int getCoffeeB() {
        return coffeeB;
    }

    public void setCoffeeB(int coffeeB) {
        this.coffeeB = coffeeB;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public void setCoffeeG(int coffeeG) {
        this.coffeeG = coffeeG;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public void setCream(int cream) {
        this.cream = cream;
    }

    public void addCoffeeB(int coffeeB) {
        this.coffeeB += coffeeB;
        this.coffeeB = this.coffeeB>1000? 1000:this.coffeeB;
    }

    public int getCoffeeG() {
        return coffeeG;
    }

    public void addCoffeeG(int coffeeG) {
        this.coffeeG += coffeeG;
        this.coffeeG = this.coffeeG>1000? 1000:this.coffeeG;
    }

    public int getCream() {
        return cream;
    }

    public void addCream(int cream) {
        this.cream += cream;
        this.cream = this.cream>200? 200:this.cream;

    }

    public int getMilk() {
        return milk;
    }

    public void addMilk(int milk) {
        this.milk += milk;
        this.milk = this.milk>500? 500:this.milk;
    }


    public int getWater() {
        return water;
    }

    public void addWater(int water) {
        this.water += water;
        this.water = this.water>1000? 1000:this.water;
    }

    public int getSugar() {
        return sugar;
    }

    public void addSugar(int sugar) {
        this.sugar += sugar;
        this.sugar = this.sugar>200? 200:this.sugar;
    }

    public void PrintInfo(){
        System.out.println("\n"+
                "\n Зерной кофе: "+coffeeB+
                "\n Молотый кофе: "+coffeeG+
                "\n Молоко: "+milk+
                "\n Вода: "+water+
                "\n Сахар: "+sugar+
                "\n Сливки:"+cream);
    }
}
