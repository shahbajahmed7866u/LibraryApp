package library;

import java.util.Scanner;

public class CustomerMenu {
    private Library library;
    private Scanner sc;

    public CustomerMenu(Library library) {
        this.library = library;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> library.displayAllBooks();
                case 2 -> borrowBook();
                case 3 -> returnBook();
                case 4 -> searchBook();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void borrowBook() {
        System.out.print("Member ID: ");
        int mid = sc.nextInt();
        System.out.print("Book ID: ");
        int bid = sc.nextInt();
        sc.nextLine();
        library.borrowBook(mid, bid);
    }

    private void returnBook() {
        System.out.print("Member ID: ");
        int mid = sc.nextInt();
        System.out.print("Book ID: ");
        int bid = sc.nextInt();
        sc.nextLine();
        library.returnBook(mid, bid);
    }

    private void searchBook() {
        System.out.print("Book Title: ");
        String title = sc.nextLine();
        library.searchBook(title);
    }
}
