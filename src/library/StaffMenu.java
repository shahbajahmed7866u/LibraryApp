package library;

import java.util.Scanner;

public class StaffMenu {
    private Library library;
    private Scanner sc;

    public StaffMenu(Library library) {
        this.library = library;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Display All Books");
            System.out.println("4. Display All Members");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> library.displayAllBooks();
                case 4 -> library.displayAllMembers();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addBook() {
        System.out.print("Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        library.addBook(new Book(id, title, author));
    }

    private void addMember() {
        System.out.print("Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        library.addMember(new Member(id, name));
    }
}
