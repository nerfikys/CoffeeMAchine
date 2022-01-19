package com.example.Spring.service;

import com.example.Spring.domain.*;
import com.example.Spring.repos.ListCommandRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class Make {
    static ArrayList<CoffeeType> Recipe = new ArrayList();//Создаём список рецептов
    static
    {
        Recipe.add(new CoffeeType("эспрессо",10,0,0,30));
        Recipe.add(new CoffeeType("американо",10,0,0,110));
        Recipe.add(new CoffeeType("капучино",10,60,0,50));
        Recipe.add(new CoffeeType("латте",10,80,0,30));
        Recipe.add(new CoffeeType("кофе по-венски",10,20,20,70));
    }
    public static boolean Make(String name, int value, int step, int sugar, State state, Capacity capacity,ListCommandRepo listCommandRepo) // Готовим кофе
    {
        listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Поступил запрос на приготовление: "+name+" "+value+" "+step+" "+sugar));
        if (!state.getName().equals("None"))
        {
            System.out.println("Кофемашина занята другим напитком");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Ошибка: Кофемашина занята другим напитком"));
            return false;
        }
        CoffeeType id=null;
        boolean flag = false; // Найден ли нужный рецепт
        for (CoffeeType coffeeType : Recipe) { //Проверяем есть ли такое кофе в списке рецептов, запоминаем номер рецепта
            if (coffeeType.getName().equals(name.toLowerCase(Locale.ROOT))) {
                id = coffeeType;
                flag = true;
                break;
            }
        }
        if (!flag) // Если кофе не было в списке
        {
            System.out.println("Рецепт не найден, приготовление отменено");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Ощибка: Рецепт не найден, приготовление отменено"));
            return false;
        }
        if (value<1)
        {
            System.out.println("Указанно слишком мало чашек, приготовление отменено");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Ошибка: Указанно слишком мало чашек, приготовление отменено"));
        }
        else if(value>2)
        {
            value=2;
            System.out.println("Указанно слишком много чашек, будет приготовленно две");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Указанно слишком много чашек, будет приготовленно две"));
        }
        if (step<0)
        {
            step=0;
            System.out.println("Указанно слишком малое значение, будет выбранно: 0");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Указанно слишком малое значение, будет выбранно: 0"));
        }
        else if(step>4)
        {
            step=4;
            System.out.println("Указанно слишком большое значение, будет выбранно максимальное: 4");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Указанно слишком большое значение, будет выбранно максимальное: 4"));
        }
        if (sugar<0)
        {
            sugar=0;
            System.out.println("Указанно слишком малое значение, будет выбранно: 0");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Указанно слишком малое значение, будет выбранно: 0"));

        }
        else if (sugar>10)
        {
            sugar=10;
            System.out.println("Указанно слишком большое значение, будет выбранно максимальное: 10");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Указанно слишком большое значение, будет выбранно максимальное: 10"));
        }
        boolean flag2=true;//Хватает ли ингредиентов
        if (value*id.getCoffee()>capacity.getCoffeeG())
        {
            System.out.println("В кофемашине недостаточно молотого кофе");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "В кофемашине недостаточно молотого кофе"));
            flag2=false;
        }
        if (value*id.getWater()>capacity.getWater())
        {
            System.out.println("В кофемашине недостаточно воды");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "В кофемашине недостаточно воды"));
            flag2=false;
        }
        if (value*id.getMilk()>capacity.getMilk())
        {
            System.out.println("В кофемашине недостаточно молока");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "В кофемашине недостаточно молока"));
            flag2=false;
        }
        if (value*id.getCream()>capacity.getCream())
        {
            System.out.println("В кофемашине недостаточно сливок");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "В кофемашине недостаточно сливок"));
            flag2=false;
        }
        if (sugar*value*10>capacity.getSugar())
        {
            System.out.println("В кофемашине недостаточно сахара");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "В кофемашине недостаточно сахара"));
            flag2=false;
        }
        if(!flag2)
        {
            System.out.println("Из-за нехватки ингредиентов приготовление остановленно");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Ошибка: Из-за нехватки ингредиентов приготовление остановленно"));
            return false;
        }
        capacity.setCoffeeG(capacity.getCoffeeB()-id.getCoffee()*value);
        if(id.getCream()>0) capacity.setCream(capacity.getCream()-id.getCream()*value);
        if(id.getMilk()>0) capacity.setMilk(capacity.getMilk()-id.getMilk()*value);
        if(sugar>0)capacity.setSugar(capacity.getSugar()-sugar*value*10);
        capacity.setWater(capacity.getWater()-id.getWater()*value);
        state.setName(name);
        state.setValue(value);
        System.out.println("Кофе приготовленно");
        listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Make", "Кофе приготовленно"));
        return true;
    }

}
