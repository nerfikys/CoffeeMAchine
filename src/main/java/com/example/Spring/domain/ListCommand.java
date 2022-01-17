package com.example.Spring.domain;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class ListCommand {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private LocalDateTime data;
    private String action;


    public ListCommand(LocalDateTime data, String action) {
        this.data = data;
        this.action = action;
    }
 //   public SaveListCommand()
}
