package org.example.controller;

import org.example.component.Library;

import java.util.Scanner;

public class BookController extends Controller {
    private final Library library;

    public BookController(Scanner scanner, Library library) {
        super(scanner);
        this.library = library;
    }

    public void manage() {
        showMenu();
        int choice = getIntInput("Enter choice: ");

        switch (choice) {
            case 1:
                handleAddBook();
                break;
            case 2:
                handleRemoveBook();
                break;
            case 3:
                handleSearchByName();
                break;
            case 4:
                handleSearchByGenre();
                break;
            case 5:
                handleSearchByAuthor();
                break;
            case 6:
                handleSearchByISBN();
                break;
            case 0:
                return; // Exit
            default:
                System.out.println("Invalid choice");
        }
    }

    private void handleAddBook() {
        String isbn = getStringInput("Enter book's ISBN ");
        String name = getStringInput("Enter book's name ");
        String genre = getStringInput("Enter book's genre ");
        String author = getStringInput("Enter book's author ");
        library.addBook(isbn, name, true, genre, author);
    }

    private void handleRemoveBook() {
        String isbn = getStringInput("Enter book's ISBN ");
        library.removeBook(isbn);
    }

    private void handleSearchByName() {
        String name = getStringInput("Enter book's name ");
        System.out.println(library.searchBookByName(name));
    }

    private void handleSearchByGenre() {
        String genre = getStringInput("Enter book's genre ");
        System.out.println(library.searchBookByGenre(genre));
    }

    private void handleSearchByAuthor() {
        String author = getStringInput("Enter book's author ");
        System.out.println(library.searchBookByAuthor(author));
    }

    private void handleSearchByISBN() {
        String isbn = getStringInput("Enter book's ISBN ");
        System.out.println(library.searchBookByISBN(isbn));
    }

    private void showMenu() {
        System.out.println("\n=== Book Management ===");
        System.out.println("1. Add book");
        System.out.println("2. Remove book");
        System.out.println("3. Search book by name");
        System.out.println("4. Search book by genre");
        System.out.println("5. Search book by author");
        System.out.println("6. Search book by ISBN");
        System.out.println("0. Exit");
    }

}
