package com.example.bookmanagement.model;

import java.util.Optional;

public class UserFilterParam {

    private int size = 10;
    private int page = 1;

    public UserFilterParam(int size, int page) {
        this.size = size;
        this.page = page;
    }

    public UserFilterParam() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
