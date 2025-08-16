/*
    Borrower.java
    -------------
    Represents a library borrower.
    Stores borrower details and list of borrowed books.
*/

import java.util.ArrayList;

public class Borrower {
    private int borrowerID;
    private String name;
    private ArrayList<Book> borrowedBooks;

    // Constructor
    public Borrower(int borrowerID, String name) {
        this.borrowerID = borrowerID;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public int getBorrowerID() {
        return borrowerID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Borrow a book
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Return a book
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}
