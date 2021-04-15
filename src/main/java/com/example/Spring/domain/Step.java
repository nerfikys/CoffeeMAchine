package com.example.Spring.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Step {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDate data;
    private Long value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Step() {
    }

    public Step(String name, LocalDate data, Long value) {
        this.name = name;
        this.data = data;
        this.value = value;
    }

    public Step(String name, LocalDate data, Long value, User author) {
        this.name = name;
        this.data = data;
        this.value = value;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
