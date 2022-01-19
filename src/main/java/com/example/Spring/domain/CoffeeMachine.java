package com.example.Spring.domain;



public class CoffeeMachine { //Кофемашина
    static Capacity cap = new Capacity(0,0,0,0,0,0); //Её наполненность
    static State state = new State("None",0,false,0);

    public static Capacity getCap() {
        return cap;
    }

    public static State getState() {
        return state;
    }
}
