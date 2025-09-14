package org.example.model.dto.users;

public class Faculty extends User {
    public Faculty(String name, String userId, String email) {
        super(name, userId, email);
    }

    @Override
    public int getMaxBooks() {
        return 10;
    }

    @Override
    public int getBorrowDays() {
        return 30;
    }

    @Override
    public double getFinePerDay() {
        return 2;
    }
}
