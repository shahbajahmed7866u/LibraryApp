package library;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    // ------------------- Book Management -------------------
    public void addBook(Book book) {
        if (findBookById(book.getBookId()) != null) {
            System.out.println("Book with ID " + book.getBookId() + " already exists!");
            return;
        }
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found: " + title);
        }
    }

    // ------------------- Member Management -------------------
    public void addMember(Member member) {
        if (findMemberById(member.getMemberId()) != null) {
            System.out.println("Member with ID " + member.getMemberId() + " already exists!");
            return;
        }
        members.add(member);
        System.out.println("Member added: " + member.getName());
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\nMembers:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // ------------------- Borrow & Return -------------------
    public void borrowBook(int memberId, int bookId) {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member == null) {
            System.out.println("Member ID not found: " + memberId);
            return;
        }
        if (book == null) {
            System.out.println("Book ID not found: " + bookId);
            return;
        }
        if (member.getBorrowedBooks().contains(book)) {
            System.out.println(member.getName() + " already borrowed \"" + book.getTitle() + "\"");
            return;
        }

        books.remove(book);
        member.borrowBook(book);
        System.out.println(member.getName() + " borrowed \"" + book.getTitle() + "\" successfully!");
    }

    public void returnBook(int memberId, int bookId) {
        Member member = findMemberById(memberId);

        if (member == null) {
            System.out.println("Member ID not found: " + memberId);
            return;
        }

        Book bookToReturn = null;
        for (Book b : member.getBorrowedBooks()) {
            if (b.getBookId() == bookId) {
                bookToReturn = b;
                break;
            }
        }

        if (bookToReturn == null) {
            System.out.println(member.getName() + " did not borrow a book with ID " + bookId);
            return;
        }

        member.returnBook(bookToReturn);
        books.add(bookToReturn);
        System.out.println(member.getName() + " returned \"" + bookToReturn.getTitle() + "\" successfully!");
    }

    // ------------------- Private Helpers -------------------
    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // ------------------- GUI-Friendly Methods -------------------

    // Return all books as single String
    public String getBooksList() {
        if (books.isEmpty()) return "No books available.";
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book).append("\n");
        }
        return sb.toString();
    }

    // Return all members as single String
    public String getMembersList() {
        if (members.isEmpty()) return "No members registered.";
        StringBuilder sb = new StringBuilder();
        for (Member member : members) {
            sb.append(member).append("\n");
        }
        return sb.toString();
    }

    // GUI-friendly book search
    public String searchBookForGUI(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book.toString();
            }
        }
        return "Book not found: " + title;
    }
}
