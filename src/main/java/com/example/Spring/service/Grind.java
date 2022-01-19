package com.example.Spring.service;

import com.example.Spring.domain.Capacity;
import com.example.Spring.domain.ListCommand;
import com.example.Spring.repos.ListCommandRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class Grind { //Перемолка кофе(Зерновый -> Молотый)

    public static boolean grind(Capacity capacity,ListCommandRepo listCommandRepo)
    {
        int b =capacity.getCoffeeB();
        int g =capacity.getCoffeeG();
        if ((g<1000)&(b>0))
        {
            g += b;
            if(g>1000)
            {
                int temp = g-1000;
                b=temp;
                g=1000;
            }
            capacity.setCoffeeB(b);
            capacity.setCoffeeG(g);
            System.out.println("Кофе перемолото");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Grind","Перемолото успешно"));
            return true;
        }
        else if ((g==1000)&(b<=0))
        {
            System.out.println("Нечего перемалывать и в этом нет необходимости");
            listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Grind","Нечего перемалывать и в этом нет необходимости"));
            return false;
        }
        else
        {
            if ((b > 0)) {
                System.out.println("Нечего перемалывать");
                listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Grind","Нечего перемалывать"));
            } else {
                System.out.println("Перемалывать нет необходимости");
                listCommandRepo.save(new ListCommand(LocalDateTime.now(),"Grind","Перемалывать нет необходимости"));
            }
            return false;
        }

    }
}
