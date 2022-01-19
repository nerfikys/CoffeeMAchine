package com.example.Spring.controller;


import com.example.Spring.domain.CoffeeMachine;
import com.example.Spring.domain.ListCommand;
import com.example.Spring.repos.*;

import com.example.Spring.service.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
public class MainController { // Приём команд через интернет
    @Autowired
    private ListCommandRepo listCommandRepo;

    @Tag(name="Молоть", description="Перемалывает зёрна")
    @GetMapping("/grind")
    public HttpStatus grind(Map<String, Object> model)
    {
        try {


            if (Grind.grind(CoffeeMachine.getCap(), listCommandRepo))
                return HttpStatus.OK;
            else return HttpStatus.BAD_REQUEST;
        }
        catch (Exception e)
        {
            return HttpStatus.CONFLICT;
        }
    }

    @Tag(name="Включить подогрев", description="Поддерживает заданную температуру")
    @GetMapping("/TurnOnTemp")
    public HttpStatus TurnOnTemp(Map<String, Object> model,
                                 @RequestParam ("temp") @Parameter(description = "Поддерживаемая температура", example = "80")  int temp)
    {
        try {
            if (TurnOnTemp.TurnOnTemp(CoffeeMachine.getState(), temp, listCommandRepo))
                return HttpStatus.OK;
            else return HttpStatus.BAD_REQUEST;
        }
        catch(Exception e)
        {
            return HttpStatus.CONFLICT;
        }

    }
    @Tag(name="Выключить подогрев", description="Выключает поддержание заданной температуры")
    @GetMapping("/TurnOffTemp")
    public HttpStatus TurnOffTemp(Map<String, Object> model)
    {
        try {
            if (TurnOffTemp.TurnOffTemp(CoffeeMachine.getState(), listCommandRepo))
                return HttpStatus.OK;
            else return HttpStatus.BAD_REQUEST;
        }
        catch (Exception e)
        {
            return HttpStatus.CONFLICT;
        }
    }

    @Tag(name="Приготовить кофе", description="Готовит кофе изходя из заданных параметров")
    @GetMapping("/Make")
    public HttpStatus Make(Map<String, Object> model,
                           @RequestParam("type") @Parameter(description = "Укажите какой кофе нужно приготовить",example = "Капучино") String type,
                           @RequestParam ("value") @Parameter(description = "Выбрать сколько чашек приготовить одну или две?",example = "1")  int value,
                           @RequestParam ("step") @Parameter(description = "Степень обжарки зёрен для аромата по силе от 0 до 4",example = "0")  int step,
                           @RequestParam ("sugar") @Parameter(description = "Сколько ложек сахара добавить на одну чашку от 0 до 10 по(10 грам)" ,example = "0")  int sugar
                            )
    {
        try
        {
            if (Make.Make(type, value, step, sugar, CoffeeMachine.getState(), CoffeeMachine.getCap(), listCommandRepo))
                return HttpStatus.OK;
            else return HttpStatus.BAD_REQUEST;
        }
        catch (Exception e)
        {
            return HttpStatus.CONFLICT;
        }
    }
    @Tag(name="Выпить(для проверки)", description="Отпивает одну чашку кофе из кофемашины")
    @GetMapping("/drink")
    public HttpStatus drink(Map<String, Object> model)
    {
        CoffeeMachine.getState().drink();
        CoffeeMachine.getState().PrintInfo();
        return HttpStatus.OK;
    }
    @Tag(name="Наполнить(для проверки)", description="Добавляет ингридиенты в кофемашину")
    @GetMapping("/full")
    public HttpStatus full(Map<String, Object> model,
                           @RequestParam("CB") @Parameter(description = "Добавить зерновой кофе 0-1000(предел), из этого через функцию \"молоть\"",example = "500") int CB,
                           @RequestParam("C") @Parameter(description = "Добавить сливки 0-200(предел)",example = "100") int C,
                           @RequestParam("M") @Parameter(description = "Добавить молоко 0-500(предел)",example = "250") int M,
                           @RequestParam("S") @Parameter(description = "Добавить сахар 0-200(предел)",example = "100") int S,
                           @RequestParam("W") @Parameter(description = "Добавить воды 0-1000(предел)",example = "500") int W
                           )
    {
        try
        {
            CoffeeMachine.getCap().addCoffeeB(CB);
            CoffeeMachine.getCap().addCream(C);
            CoffeeMachine.getCap().addMilk(M);
            CoffeeMachine.getCap().addSugar(S);
            CoffeeMachine.getCap().addWater(W);
            CoffeeMachine.getCap().PrintInfo();
            return HttpStatus.OK;
        }
        catch (Exception e)
        {
            return HttpStatus.CONFLICT;
        }
    }


}
