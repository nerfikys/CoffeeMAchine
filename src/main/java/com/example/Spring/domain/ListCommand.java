package com.example.Spring.domain;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class ListCommand { // Таблица в бд для учёта работы
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private LocalDateTime data;
    private String action;
    private String result;

    public ListCommand() {
    }

    public ListCommand(LocalDateTime data, String action, String result) {
        this.data = data;
        this.action = action;
        this.result = result;
    }
}
