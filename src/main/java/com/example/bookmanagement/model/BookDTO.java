package com.example.bookmanagement.model;

import java.util.UUID;

public class BookDTO {

    private String id;
    private String userId;
    private String name;
    private String description;
    private String author;

    public BookDTO(String id, String name, String description, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public BookDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BookDTO(String id, String userId, String name, String description, String author) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public BookDTO() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
