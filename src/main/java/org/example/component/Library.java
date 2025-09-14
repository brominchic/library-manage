package org.example.component;

import org.example.model.dto.Book;
import org.example.model.dto.BorrowingRecord;
import org.example.model.dto.users.Faculty;
import org.example.model.dto.users.Guest;
import org.example.model.dto.users.Student;
import org.example.model.dto.users.User;

import java.time.LocalDate;
import java.util.*;

public class Library implements LibraryOperations {
    // Use HashMap for fast lookup by key
    private final Map<String, Book> books;      // ISBN -> Book
    private final Map<String, User> users;      // UserID -> User

    // Use List for ordered collections
    private final List<BorrowingRecord> borrowingHistory;

    // Use Set for unique collections
    private final Set<String> genres;

    public Library() {
        books = new HashMap<>();           // O(1) book lookup
        users = new HashMap<>();           // O(1) user lookup
        borrowingHistory = new ArrayList<>(); // Chronological order
        genres = new HashSet<>();          // Unique genres only
    }

    @Override
    public boolean borrowBook(String userId, String isbn) {
        User user = users.get(userId);     // Fast lookup
        Book book = books.get(isbn);       // Fast lookup
        // Validation logic
        if (user == null || book == null) {
            System.out.println("USER OR BOOK IS NULL");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("BOOK IS UNAVAILABLE");
            return false;
        }

        if (!user.canBorrow()) {
            System.out.println("USER CAN'T BORROW");
            return false;
        }

        // Process borrowing
        book.setAvailable(false);
        user.getBorrowedBooks().add(isbn);
        borrowingHistory.add(new BorrowingRecord(user, book, LocalDate.now()));
        return true;
    }

    @Override
    public boolean returnBook(String userId, String isbn) {
        User user = users.get(userId);
        Book book = books.get(isbn);

        // Validation logic
        if (user == null || book == null) {
            System.out.println("USER OR BOOK IS NULL");
            return false;
        }
        if (book.isAvailable()) {
            System.out.println("BOOK IS ALREADY AVAILABLE");
            return false;
        }

        // Process returning
        book.setAvailable(true);
        user.getBorrowedBooks().remove(isbn);
        for (int i = 0; i < borrowingHistory.size(); i++) {
            BorrowingRecord record = borrowingHistory.get(i);
            if (record.getUser().getUserId().equals(user.getUserId()) & record.getBook().getIsbn().equals(book.getIsbn())) {
                borrowingHistory.remove(i);
            }
        }
        return true;
    }

    @Override
    public void addBook(String isbn, String name, Boolean isAvailable, String genre, String author) {
        genres.add(genre);
        books.put(isbn, new Book(isbn, name, isAvailable, genre, author));
    }

    @Override
    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    @Override
    public String searchBookByName(String name) {
        StringBuilder booksNames = new StringBuilder("ISBN      Name \n");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            String isbn = entry.getKey();
            Book book = entry.getValue();
            if (book.getName().contains(name)) {
                booksNames.append(isbn).append("        ").append(book.getName()).append("\n");
            }
        }
        return booksNames.toString();
    }

    @Override
    public String searchBookByGenre(String genre) {
        if (!genres.contains(genre)) {
            return "No books in this genre";
        }
        StringBuilder booksNames = new StringBuilder("ISBN      Name \n");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            String isbn = entry.getKey();
            Book book = entry.getValue();
            if (book.getGenre().equals(genre)) {
                booksNames.append(isbn).append("        ").append(book.getName()).append("\n");
            }
        }
        return booksNames.toString();
    }

    @Override
    public String searchBookByAuthor(String author) {
        StringBuilder booksNames = new StringBuilder("ISBN      Name \n");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            String isbn = entry.getKey();
            Book book = entry.getValue();
            if (book.getAuthor().contains(author)) {
                booksNames.append(isbn).append("        ").append(book.getName()).append("\n");
            }
        }
        return booksNames.toString();
    }

    @Override
    public String searchBookByISBN(String isbn) {
        String booksNames = "ISBN      Name \n";
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            if (book.getIsbn().equals(isbn)) {
                booksNames += isbn + "        " + book.getName() + "\n";
            }
        }
        return booksNames;
    }

    @Override
    public void addUser(int role, String name, String userId, String email) {
        switch (role) {
            case 1:
                users.put(userId, new Student(name, userId, email));
                break;
            case 2:
                users.put(userId, new Faculty(name, userId, email));
                break;
            case 3:
                users.put(userId, new Guest(name, userId, email));
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    @Override
    public Map<String, User> getUsers() {
        return users;
    }

    @Override
    public void viewOverdueBooks() {
        LocalDate date = LocalDate.now();
        StringBuilder overdueBooks = new StringBuilder();
        for (BorrowingRecord record : borrowingHistory) {
            if (date.isAfter(record.getDate().plusDays(record.getUser().getBorrowDays()))) {
                if (!record.isReturned()) {
                    overdueBooks.append("ISBN:").append(record.getBook().getName()).append(" User id: ").append(record.getUser().getName()).append("\n");
                }
            }
        }
        if (overdueBooks.isEmpty()) {
            System.out.println("No overdue books");
        } else {
            System.out.println(overdueBooks);
        }
    }

}
