package org.example.controller;

import org.example.component.Library;

import java.util.Scanner;

public class LibraryConsole {
    private final Library library;
    private final Scanner scanner;
    private final BookController bookController;
    private final UserController userController;
    private final BorrowController borrowController;

    public LibraryConsole() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
        this.bookController = new BookController(scanner, library);
        this.userController = new UserController(scanner, library);
        this.borrowController = new BorrowController(scanner, library);
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    handleBookManagement();
                    break;
                case 2:
                    handleUserManagement();
                    break;
                case 3:
                    handleBorrowing();
                    break;
                case 0:
                    return; // Exit
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void handleBookManagement() {
        bookController.manage();
    }

    private void handleUserManagement() {
        userController.manage();
    }

    private void handleBorrowing() {
        borrowController.manage();
    }

    private void showMenu() {
        System.out.println("\n=== Library Management ===");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Borrowing Operations");
        System.out.println("0. Exit");
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
