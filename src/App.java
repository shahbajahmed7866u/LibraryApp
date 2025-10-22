import library.Library;
import library.StaffMenu;
import library.CustomerMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        final String STAFF_PASSWORD = "lib@786"; // staff password
        final int MAX_ATTEMPTS = 3;

        while (running) {
            System.out.println("\n=== Library System ===");
            System.out.println("1. Staff");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    boolean authenticated = false;
                    for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
                        System.out.print("Enter Staff Password: ");
                        String input = sc.nextLine();
                        if (STAFF_PASSWORD.equals(input)) {
                            authenticated = true;
                            break;
                        } else {
                            System.out.println("Incorrect password! Attempt " + attempt + " of " + MAX_ATTEMPTS);
                        }
                    }
                    if (authenticated) {
                        new StaffMenu(library).start();
                    } else {
                        System.out.println("Access denied. Returning to main menu.");
                    }
                }
                case 2 -> new CustomerMenu(library).start();
                case 3 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
