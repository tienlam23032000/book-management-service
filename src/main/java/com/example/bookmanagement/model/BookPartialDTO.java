package com.example.bookmanagement.model;

import java.util.Optional;

public class BookPartialDTO {
    private Optional<String> id;
    private Optional<String> userId;
    private Optional<String> name;
    private Optional<String> description;
    private Optional<String> author;

    public BookPartialDTO() {
        // do nothing
    }

    public void setId(String id) {
        this.id = Optional.ofNullable(id);
    }

    public Optional<String> getId() {
        return this.id;
    }

    public void setUserId(String userId) {
        this.userId = Optional.ofNullable(userId);
    }

    public Optional<String> getUserId() {
        return this.userId;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<String> getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public Optional<String> getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = Optional.ofNullable(author);
    }

    public Optional<String> getDescription() {
        return this.description;
    }

}
