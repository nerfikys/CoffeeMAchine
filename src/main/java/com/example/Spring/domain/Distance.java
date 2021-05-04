package com.example.Spring.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Distance {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDate data;
    private Double value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Distance() {
    }

    public Distance(String name, LocalDate data, Double value) {
        this.name = name;
        this.data = data;
        this.value = value;
    }

    public Distance(String name, LocalDate data, Double value, User author) {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Objects.equals(id, distance.id) &&
                Objects.equals(name, distance.name) &&
                Objects.equals(data, distance.data) &&
                Objects.equals(value, distance.value) &&
                Objects.equals(author, distance.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, data, value, author);
    }
}
