package com.example.bookmanagement.model;

import java.util.Optional;

public class UserPartialDTO {

    private Optional<String> id;

    private Optional<String> name;

    public UserPartialDTO(String id, String name) {
        this.id = Optional.ofNullable(id);;
        this.name = Optional.ofNullable(name);
    }

    public UserPartialDTO() {
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Optional.ofNullable(id);
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }
}
