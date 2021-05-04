package com.example.Spring.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Weight {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDateTime data;
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Weight() {
    }

    public Weight(String name, LocalDateTime data, String value) {
        this.name = name;
        this.data = data;
        this.value = value;
    }

    public Weight(String name, LocalDateTime data, String value, User author) {
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Objects.equals(id, weight.id) &&
                Objects.equals(name, weight.name) &&
                Objects.equals(data, weight.data) &&
                Objects.equals(value, weight.value) &&
                Objects.equals(author, weight.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, data, value, author);
    }
}
