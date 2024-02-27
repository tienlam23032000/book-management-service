package com.example.bookmanagement.model;

import java.util.List;

public class BookListDTO {

    private List<BookDTO> data;

    private Href href;

    public BookListDTO(List<BookDTO> data, Href href) {
        this.data = data;
        this.href = href;
    }

    public BookListDTO() {
    }

    public List<BookDTO> getData() {
        return data;
    }

    public void setData(List<BookDTO> data) {
        this.data = data;
    }

    public Href getHref() {
        return href;
    }

    public void setHref(Href href) {
        this.href = href;
    }
}
