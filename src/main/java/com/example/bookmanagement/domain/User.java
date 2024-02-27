package com.example.bookmanagement.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book_user")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "name")
    private String name;

//    @Transient // ignore trên mặt db
//    @JsonIgnore // ignore trên web (postman)
    @OneToMany(mappedBy = "user")
    private List<Book> books;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public User(String id, String name, List<Book> books, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.isDeleted = isDeleted;
    }

    public User(String id, String name, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", books=" + books +
                '}';
    }
}
