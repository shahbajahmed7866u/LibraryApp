package library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int memberId;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Member(int memberId, String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (memberId <= 0) {
            throw new IllegalArgumentException("Member ID must be positive");
        }
        this.memberId = memberId;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name;
    }
}
