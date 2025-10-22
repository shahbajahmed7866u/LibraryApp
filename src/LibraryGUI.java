import library.Library;
import library.Book;
import library.Member;

import javax.swing.*;
import java.awt.*;

public class LibraryGUI {

    private JFrame frame;
    private JPanel panel;
    private int passwordAttempts = 0;

    public LibraryGUI() {
        frame = new JFrame("Ahmed Library");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.setContentPane(panel);

        showMainMenu();
        frame.setVisible(true);
    }

    private void showMainMenu() {
        panel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;

        JButton staffBtn = new JButton("Staff");
        JButton customerBtn = new JButton("Customer");
        JButton exitBtn = new JButton("Exit");

        gbc.gridy = 0;
        panel.add(staffBtn, gbc);
        gbc.gridy = 1;
        panel.add(customerBtn, gbc);
        gbc.gridy = 2;
        panel.add(exitBtn, gbc);

        staffBtn.addActionListener(e -> checkStaffPassword());
        customerBtn.addActionListener(e -> showCustomerMenu());
        exitBtn.addActionListener(e -> frame.dispose());

        panel.revalidate();
        panel.repaint();
    }

    private void checkStaffPassword() {
        while (passwordAttempts < 3) {
            String password = JOptionPane.showInputDialog(frame, "Enter Staff Password:");
            if (password == null) return;
            if ("lib@786".equals(password)) {
                JOptionPane.showMessageDialog(frame, "Welcome, Staff!");
                showStaffMenu();
                return;
            } else {
                passwordAttempts++;
                JOptionPane.showMessageDialog(frame, "Incorrect Password! Attempt " + passwordAttempts + "/3");
            }
        }
        JOptionPane.showMessageDialog(frame, "Too many incorrect attempts! Access denied.");
    }

    private void showStaffMenu() {
        panel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JButton addBookBtn = new JButton("Add Book");
        JButton addMemberBtn = new JButton("Add Member");
        JButton viewBooksBtn = new JButton("View All Books");
        JButton viewMembersBtn = new JButton("View All Members");
        JButton backBtn = new JButton("Back to Main Menu");

        gbc.gridy = 0;
        panel.add(addBookBtn, gbc);
        gbc.gridy = 1;
        panel.add(addMemberBtn, gbc);
        gbc.gridy = 2;
        panel.add(viewBooksBtn, gbc);
        gbc.gridy = 3;
        panel.add(viewMembersBtn, gbc);
        gbc.gridy = 4;
        panel.add(backBtn, gbc);

        addBookBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Book ID:"));
                String title = JOptionPane.showInputDialog(frame, "Enter Book Title:");
                String author = JOptionPane.showInputDialog(frame, "Enter Author Name:");
                Library.addBook(new Book(id, title, author));
                JOptionPane.showMessageDialog(frame, "Book added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        addMemberBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Member ID:"));
                String name = JOptionPane.showInputDialog(frame, "Enter Member Name:");
                Library.addMember(new Member(id, name));
                JOptionPane.showMessageDialog(frame, "Member added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        viewBooksBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            Library.getBooks().forEach(book -> sb.append(book).append("\n"));
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "No books available.");
        });

        viewMembersBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            Library.getMembers().forEach(member -> sb.append(member).append("\n"));
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "No members registered.");
        });

        backBtn.addActionListener(e -> showMainMenu());

        panel.revalidate();
        panel.repaint();
    }

    private void showCustomerMenu() {
        panel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JButton viewBooksBtn = new JButton("View All Books");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton searchBtn = new JButton("Search Book");
        JButton backBtn = new JButton("Back to Main Menu");

        gbc.gridy = 0;
        panel.add(viewBooksBtn, gbc);
        gbc.gridy = 1;
        panel.add(borrowBtn, gbc);
        gbc.gridy = 2;
        panel.add(returnBtn, gbc);
        gbc.gridy = 3;
        panel.add(searchBtn, gbc);
        gbc.gridy = 4;
        panel.add(backBtn, gbc);

        viewBooksBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            Library.getBooks().forEach(book -> sb.append(book).append("\n"));
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "No books available.");
        });

        borrowBtn.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Member ID:"));
                int bookId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Book ID:"));
                boolean success = Library.borrowBook(memberId, bookId);
                JOptionPane.showMessageDialog(frame, success ? "Book borrowed successfully!" : "Borrow failed!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        returnBtn.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Member ID:"));
                int bookId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Book ID:"));
                boolean success = Library.returnBook(memberId, bookId);
                JOptionPane.showMessageDialog(frame, success ? "Book returned successfully!" : "Return failed!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        searchBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Enter Book Title to Search:");
            if (title != null && !title.isEmpty()) {
                Book found = Library.getBooks().stream()
                        .filter(b -> b.getTitle().equalsIgnoreCase(title))
                        .findFirst()
                        .orElse(null);
                JOptionPane.showMessageDialog(frame, found != null ? "Found: " + found : "Book not found!");
            }
        });

        backBtn.addActionListener(e -> showMainMenu());

        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}
