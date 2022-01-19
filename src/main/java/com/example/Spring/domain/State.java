package com.example.Spring.domain;

public class State {
    private String name = "None"; // Каким кофе машина наполнена(остаток после приготовления)
    private int value =0;// Сколько осталось чашек напитка
    private boolean TurmTemp = false;//Включена ли функция подогрева
    private int tenperature =0;// На какую температуру

    public State() {
    }

    public State(String name, int value, boolean turmTemp, int tenperature) {
        this.name = name;
        this.value = value;
        TurmTemp = turmTemp;
        this.tenperature = tenperature;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTurmTemp() {
        return TurmTemp;
    }

    public void setTurmTemp(boolean turmTemp) {
        TurmTemp = turmTemp;
    }

    public int getTenperature() {
        return tenperature;
    }

    public void setTenperature(int tenperature) {
        this.tenperature = tenperature;
    }
    public void PrintInfo(){
        if(!name.equals("None"))
        {
            System.out.println("\n"+
                "\n Кофе внутри: "+name+
                "\n Сколько чашек осталось: "+value);
            if(TurmTemp) System.out.println("Подогрев включён на: "+tenperature);
            else System.out.println("Подогрев выключен");
        }
        else System.out.println("В кофемашине нет готового напитка");
    }
    public void drink()//Выпить чашку кофе
    {
        if (value==0) System.out.println("Кофемашина пуста");
        else if(value==1) //Если была одна чашку выпить и отключить функцию подогрева, если она включена
        {
            value--;
            name="None";
            if(TurmTemp)
            {
                TurmTemp=false;
                tenperature=0;
            }
            System.out.println("Последня чашка выпита");
        }
        else
        {
            System.out.println("Одна чашка выпита, одна осталась");
            value--;
        }

    }
}
