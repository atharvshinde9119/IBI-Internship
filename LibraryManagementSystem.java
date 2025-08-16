/*
    LibraryManagementSystem.java
    -----------------------------
    Main class for running the library system.
    Provides menu-driven console interface.
*/

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Borrower> borrowers = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> searchBook();
                case 6 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author, true));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println("ID: " + b.getBookID() +
                               " | Title: " + b.getTitle() +
                               " | Author: " + b.getAuthor() +
                               " | Available: " + (b.isAvailable() ? "Yes" : "No"));
        }
    }

    private static void borrowBook() {
        System.out.print("Enter Borrower ID: ");
        int borrowerID = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Borrower Name: ");
        String name = sc.nextLine();

        Borrower borrower = findBorrowerById(borrowerID);
        if (borrower == null) {
            borrower = new Borrower(borrowerID, name);
            borrowers.add(borrower);
        }

        System.out.print("Enter Book ID to borrow: ");
        int bookID = sc.nextInt();

        Book book = findBookById(bookID);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Book already borrowed.");
        } else {
            book.setAvailable(false);
            borrower.borrowBook(book);
            System.out.println("Book borrowed successfully!");
        }
    }

    private static void returnBook() {
        System.out.print("Enter Borrower ID: ");
        int borrowerID = sc.nextInt();

        Borrower borrower = findBorrowerById(borrowerID);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        System.out.print("Enter Book ID to return: ");
        int bookID = sc.nextInt();

        Book book = findBookById(bookID);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            book.setAvailable(true);
            borrower.returnBook(book);
            System.out.println("Book returned successfully!");
        }
    }

    private static void searchBook() {
        System.out.print("Enter Title or Author to search: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword) || 
                b.getAuthor().toLowerCase().contains(keyword)) {
                System.out.println("ID: " + b.getBookID() +
                                   " | Title: " + b.getTitle() +
                                   " | Author: " + b.getAuthor() +
                                   " | Available: " + (b.isAvailable() ? "Yes" : "No"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private static Book findBookById(int id) {
        for (Book b : books) {
            if (b.getBookID() == id) return b;
        }
        return null;
    }

    private static Borrower findBorrowerById(int id) {
        for (Borrower br : borrowers) {
            if (br.getBorrowerID() == id) return br;
        }
        return null;
    }
}
