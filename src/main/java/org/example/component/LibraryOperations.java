package org.example.component;

import org.example.model.dto.users.User;

import java.util.Map;

public interface LibraryOperations {
    boolean borrowBook(String userId, String isbn);

    boolean returnBook(String userId, String isbn);

    void addBook(String isbn, String name, Boolean isAvailable, String genre, String author);

    void removeBook(String isbn);

    String searchBookByName(String name);

    String searchBookByGenre(String genre);

    String searchBookByAuthor(String author);

    String searchBookByISBN(String author);

    void addUser(int role, String name, String userId, String email);

    Map<String, User> getUsers();

    void viewOverdueBooks();
}
