package com.example.Spring.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(id, step.id) &&
                Objects.equals(name, step.name) &&
                Objects.equals(data, step.data) &&
                Objects.equals(value, step.value) &&
                Objects.equals(author, step.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, data, value, author);
    }
}
