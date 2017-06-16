package com.example.Nesrine.myapplication.backend;

/**
 * Created by Nesrine on 30/04/2017.
 */

public class Book {

    private String title;
    private  String isbn;
    private String category;

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
