package com.example.bookmanagement.model;

public class Href {

    private String next;

    private String previous;

    private String size;

    public Href(String next, String previous, String size) {
        this.next = next;
        this.previous = previous;
        this.size = size;
    }

    public Href() {
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
