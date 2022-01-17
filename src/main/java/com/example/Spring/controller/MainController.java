package com.example.Spring.controller;


import com.example.Spring.domain.ListCommand;
import com.example.Spring.repos.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.*;

@RestController
public class MainController {
    @Autowired
    private ListCommandRepo listCommandRepo;

    @Tag(name="Молоть", description="Перемалывает зёрна")
    @GetMapping("/grind")
    public HttpStatus grind(Map<String, Object> model)
    {
        listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Grind"));
        return HttpStatus.OK;
    }

    @Tag(name="Включить подогрев", description="Поддерживает заданную температуру")
    @GetMapping("/TurnOnTemp")
    public HttpStatus TurnOnTemp(Map<String, Object> model,
                                 @RequestParam ("temp") @Parameter(description = "Поддерживаемая температура", example = "80")  int temp)
    {
        listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp = "+temp ));
        return HttpStatus.OK;
    }
    @Tag(name="Выключить подогрев", description="Выключает поддержание заданной температуры")
    @GetMapping("/TurnOffTemp")
    public HttpStatus TurnOffTemp(Map<String, Object> model)
    {
        listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOffTemp"));
        return HttpStatus.OK;
    }
    @Tag(name="Помыть", description="Включает функцию ")
    @GetMapping("/Wash")
    public HttpStatus Wash(Map<String, Object> model)
    {
        listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Wash"));
        return HttpStatus.OK;
    }

    @Tag(name="Приготовить кофе", description="Готовит кофе изходя из заданых параметров")
    @GetMapping("/Make")
    public HttpStatus Make(Map<String, Object> model,
                           @RequestParam("type") @Parameter(description = "Укажите какой кофе нужно приготовить",example = "Капучино") String type,
                           @RequestParam ("value") @Parameter(description = "Выбрать объём приготовляваемого кофе в диапазоне 120-350 мл",example = "200")  int value,
                           @RequestParam ("step") @Parameter(description = "Степень обжарки зёрен для аромата по силе от 0 до 4",example = "0")  int step,
                           @RequestParam ("sugar") @Parameter(description = "Сколько ложек сахара добавить на одну чашку(120-150мл) от 0 до 10" ,example = "0")  int sugar,
                           @RequestParam ("time") @Parameter(description = "Через сколько минут приготовить  от 0 до 1439",example = "0" )  int time
                           )
    {
        try{
        HashSet<String> coffee = new HashSet<>();
        coffee.add("Эспрессо");
        coffee.add("Американо");
        coffee.add("Капучино");
        coffee.add("Латте");
        coffee.add("Мокко");
        if(coffee.contains(type)) {
            value = value>350? 350:value;
            value = value<120? 120:value;

            step = step>4? 4:step;
            step = step<0? 0:step;

            sugar = sugar>10? 10:sugar;
            sugar = sugar<0? 0:sugar;

            time = time>1439? 1439:time;
            time = time<0? 0:time;

            listCommandRepo.save(new ListCommand(LocalDateTime.now(), "Making "+type+" "+value+" "+step+" "+sugar+" "+time));
            return HttpStatus.OK;
        }
        else
        {
            listCommandRepo.save(new ListCommand(LocalDateTime.now(), "Make-BAD_REQUEST"));
            return HttpStatus.BAD_REQUEST;

        }
        } catch (Exception e) {
            e.printStackTrace();
            listCommandRepo.save(new ListCommand(LocalDateTime.now(), "Make-BAD_REQUEST"));
            return HttpStatus.BAD_REQUEST;
        }
    }

}
