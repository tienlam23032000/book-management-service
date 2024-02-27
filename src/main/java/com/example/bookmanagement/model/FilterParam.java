package com.example.bookmanagement.model;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class FilterParam {

    private Optional<String> author;
    private int size = 10;
    private int page = 1;

    public FilterParam(Optional<String> author, int size, int page) {
        this.author = author;
        this.size = size;
        this.page = page;
    }

    public FilterParam() {
    }

    public Optional<String> getAuthor() {
        return author;
    }

    public void setAuthor(Optional<String> author) {
        this.author = author;
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
