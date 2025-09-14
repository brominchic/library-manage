package org.example.model.dto;

public class Book {
    private final String isbn;
    private final String name;
    private Boolean isAvailable;
    private final String genre;
    private final String author;

    public Book(String isbn, String name, Boolean isAvailable, String genre, String author) {
        this.isbn = isbn;
        this.name = name;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

}
