package org.example.controller;

import org.example.component.Library;

import java.util.Scanner;

public class UserController extends Controller {
    private final Library library;
    private final int lastUserId;

    public UserController(Scanner scanner, Library library) {
        super(scanner);
        this.library = library;
        this.lastUserId = 0;
    }

    public void manage() {
        showMenu();
        int choice = getIntInput("Enter choice: ");

        switch (choice) {
            case 1:
                createUser(1);
                break;
            case 2:
                createUser(2);
                break;
            case 3:
                createUser(3);
                break;
            case 4:
                getUsers();
                break;
            case 0:
                return; // Exit
            default:
                System.out.println("Invalid choice");
        }
    }

    public void createUser(int role) {
        String name = getStringInput("Enter user's name ");
        String email = getStringInput("Enter user's email ");
        library.addUser(role, String.valueOf(lastUserId), name, email);
    }

    public void getUsers() {
        System.out.println(library.getUsers());
    }

    private void showMenu() {
        System.out.println("\n=== USER Management ===");
        System.out.println("1. Add Student");
        System.out.println("2. Add Faculty");
        System.out.println("3. Add Guest");
        System.out.println("4. Get Users");
        System.out.println("0. Exit");
    }
}
