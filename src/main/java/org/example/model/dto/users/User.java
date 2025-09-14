package org.example.model.dto.users;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String name;
    protected String userId;
    protected String email;
    protected List<String> borrowedBooks;

    /*
    Creates User with name, userId, email
     */
    public User(String name, String userId, String email) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    /*
       @return true if User can borrow book
     */
    public boolean canBorrow() {
        return borrowedBooks.size() < getMaxBooks();
    }

    /*
       @return max amount of user books
     */
    public abstract int getMaxBooks();

    /*
       @return max amount of borrowDays
     */
    public abstract int getBorrowDays();

    /*
       @return fine per day
     */
    public abstract double getFinePerDay();

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
