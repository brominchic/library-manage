package org.example.controller;

import org.example.component.Library;

import java.util.Scanner;

public class BorrowController extends Controller {
    private final Library library;

    public BorrowController(Scanner scanner, Library library) {
        super(scanner);
        this.library = library;
    }

    public void manage() {
        showMenu();
        int choice = getIntInput("Enter choice: ");
        switch (choice) {
            case 1:
                borrowBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                viewOverdueBooks();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    public void borrowBook() {
        String userId = getStringInput("Enter user's id ");
        String isbn = getStringInput("Enter book's ISBN ");
        if (!library.borrowBook(userId, isbn)) {
            System.out.println("ERROR");
        }
    }

    public void returnBook() {
        String userId = getStringInput("Enter user's id ");
        String isbn = getStringInput("Enter book's ISBN ");
        if (!library.returnBook(userId, isbn)) {
            System.out.println("ERROR");
        }
    }

    public void viewOverdueBooks() {
        library.viewOverdueBooks();
    }

    private void showMenu() {
        System.out.println("\n=== Borrow Management ===");
        System.out.println("1. Borrow book");
        System.out.println("2. Return book");
        System.out.println("3. View overdue books");
        System.out.println("0. Exit");
    }
}
