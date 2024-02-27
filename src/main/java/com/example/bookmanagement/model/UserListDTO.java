package com.example.bookmanagement.model;

import java.util.List;

public class UserListDTO {


    private List<UserDTO> data;

    private Href href;

    public UserListDTO(List<UserDTO> data, Href href) {
        this.data = data;
        this.href = href;
    }

    public UserListDTO() {
    }

    public List<UserDTO> getData() {
        return data;
    }

    public void setData(List<UserDTO> data) {
        this.data = data;
    }

    public Href getHref() {
        return href;
    }

    public void setHref(Href href) {
        this.href = href;
    }
}
