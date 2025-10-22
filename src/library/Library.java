package library;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Member> members = new ArrayList<>();

    // ======= Book Management =======
    public static void addBook(Book book) {
        if (findBookById(book.getBookId()) != null) {
            System.out.println("Book with ID " + book.getBookId() + " already exists!");
            return;
        }
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book);
                return book;
            }
        }
        System.out.println("Book not found: " + title);
        return null;
    }

    // ======= Member Management =======
    public static void addMember(Member member) {
        if (findMemberById(member.getMemberId()) != null) {
            System.out.println("Member with ID " + member.getMemberId() + " already exists!");
            return;
        }
        members.add(member);
        System.out.println("Member added: " + member.getName());
    }

    public static List<Member> getMembers() {
        return members;
    }

    public static void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\nMembers:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // ======= Borrow / Return System =======
    public static boolean borrowBook(int memberId, int bookId) {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member == null || book == null) return false;
        if (member.getBorrowedBooks().contains(book)) return false;

        books.remove(book);
        member.borrowBook(book);
        return true;
    }

    public static boolean returnBook(int memberId, int bookId) {
        Member member = findMemberById(memberId);
        if (member == null) return false;

        Book bookToReturn = null;
        for (Book b : member.getBorrowedBooks()) {
            if (b.getBookId() == bookId) {
                bookToReturn = b;
                break;
            }
        }
        if (bookToReturn == null) return false;

        member.returnBook(bookToReturn);
        books.add(bookToReturn);
        return true;
    }

    // ======= Helper Methods =======
    private static Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) return member;
        }
        return null;
    }

    private static Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) return book;
        }
        return null;
    }
}
