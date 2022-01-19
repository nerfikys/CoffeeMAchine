package com.example.Spring.service;

import com.example.Spring.domain.ListCommand;
import com.example.Spring.domain.State;
import com.example.Spring.repos.ListCommandRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TurnOnTemp { //Включение подогрева
    @Autowired
    private static ListCommandRepo listCommandRepo;
    public static boolean TurnOnTemp(State state,int temp,ListCommandRepo listCommandRepo)
    {
        if (state.getName().equals("None"))
        {
            System.out.println("Кофемашина пуста, нечего подогревать");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp","Ошибка: Кофемашина пуста, нечего подогревать"));
            return false;
        }
        if(temp<=0) // Проверка введёной температуры на минимальное значение
        {
            System.out.println("Указана слишком маленькая температура, действие отменено");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp","Ошибка: Указана слишком маленькая температура, действие отменено, указанная температура: "+temp));
            return false;
        }
        else if(temp>100) // Проверка введёной температуры на максимальное значение
        {

            System.out.println("Указана слишком большая температура, установлено максимальное значение");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp","Указана слишком большая температура, установлено максимальное значение, указанная температура: "+temp));
            temp=100;
        }
        if (!state.isTurmTemp()) //проверка режима подогрева и если подогрев выключен
        {
            state.setTurmTemp(true);
            state.setTenperature(temp);
            System.out.println("Подогрев включен");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp","Подогрев включён, поддерживаемая температура: "+temp));
            return true;
        }
        else if(state.getTenperature()==temp) // Если подогрев включён и указана такая же температура
        {
            System.out.println("Подогрев уже включён на температуру: "+temp);
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp","Ошибка: Подогрев уже включён на температуру: "+temp));
            return true;
        }
        else //Если подогрев включён и указана другая температура
        {
            state.setTenperature(temp);
            System.out.println("Подогрев изменён на температуру: "+temp);
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"TurnOnTemp","Подогрев изменён на температуру: "+temp));
            return true;
        }
    }
}
