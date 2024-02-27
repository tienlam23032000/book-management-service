package com.example.bookmanagement.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Book {

    @Id
    @Column(name = "book_id")
    private String id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Book(String id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Book(String id, String name, String description, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public Book(String id, String name, String description, String author, User user,
                boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.user = user;
        this.isDeleted = isDeleted;
    }

    public Book() {
        // do nothing
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Book) {
            return this.id == ((Book) o).getId() && this.name.equals(((Book) o).getName())
                    && this.description.equals(((Book) o).getDescription());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user.toString() +
                '}';
    }
}
