package com.example.Spring.service;

import com.example.Spring.domain.ListCommand;
import com.example.Spring.domain.State;
import com.example.Spring.repos.ListCommandRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TurnOffTemp { // Выключение подогрева
    @Autowired
    private static ListCommandRepo listCommandRepo;
    public static boolean TurnOffTemp(State state,ListCommandRepo listCommandRepo)
    {
        if (state.isTurmTemp()) // Проверка что он включён
        {
            state.setTurmTemp(false);
            state.setTenperature(0);
            System.out.println("Подогрев выключен");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOffTemp","Подогрев выключен"));
            return true;
        }
        else //Если он выключен
        {
            System.out.println("Подогрев не был включён");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOffTemp","Ошибка:Подогрев не был включён"));
            return false;
        }
    }
}
