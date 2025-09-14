package org.example.model.dto;

import org.example.model.dto.users.User;

import java.time.LocalDate;

public class BorrowingRecord {
    protected User user;
    protected Book book;
    protected LocalDate date;
    protected boolean isReturned;

    public BorrowingRecord(User user, Book book, LocalDate date) {
        this.user = user;
        this.book = book;
        this.date = date;
        this.isReturned = false;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isReturned() {
        return isReturned;
    }

}
