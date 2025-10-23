import library.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class LibraryGUI extends JFrame {

    private Library library;
    private JPanel mainPanel;

    public LibraryGUI() {
        library = new Library();

        setTitle("Ahmed Library");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        showMainMenu();
    }

    private void showMainMenu() {
        mainPanel.removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel title = new JLabel("Ahmed Library");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(70, 70, 70));

        JButton staffBtn = new JButton("Staff Mode");
        JButton customerBtn = new JButton("Customer Mode");
        JButton exitBtn = new JButton("Exit");

        staffBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        staffBtn.setMaximumSize(new Dimension(200, 40));
        customerBtn.setMaximumSize(new Dimension(200, 40));
        exitBtn.setMaximumSize(new Dimension(200, 40));

        staffBtn.addActionListener(e -> authenticateStaff());
        customerBtn.addActionListener(e -> showCustomerMenu());
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(title);
        panel.add(Box.createVerticalStrut(40));
        panel.add(staffBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(customerBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(exitBtn);

        mainPanel.add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void authenticateStaff() {
        int attempts = 0;

        while (attempts < 3) {
            String input = JOptionPane.showInputDialog(this, "Enter Staff Password:");
            if (input == null) return; // cancel

            if (authenticate(input)) {
                showStaffMenu();
                return;
            } else {
                attempts++;
                JOptionPane.showMessageDialog(this, "Incorrect password! Attempts left: " + (3 - attempts));
            }
        }

        JOptionPane.showMessageDialog(this, "Too many failed attempts! Returning to main menu.");
        showMainMenu();
    }

    private boolean authenticate(String inputPassword) {
        try {
            File file = new File("internalData/password.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(this,
                        "Password file missing! Please create 'internalData/password.txt' in project root.");
                return false;
            }

            Scanner sc = new Scanner(file);
            String actualPassword = sc.nextLine().trim();
            sc.close();

            return inputPassword.equals(actualPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading password file!");
            return false;
        }
    }

    // ================= Staff Menu =================
    private void showStaffMenu() {
        mainPanel.removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JLabel title = new JLabel("Staff Menu");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addBook = new JButton("Add Book");
        JButton addMember = new JButton("Add Member");
        JButton displayBooks = new JButton("Display Books");
        JButton displayMembers = new JButton("Display Members");
        JButton back = new JButton("Return to Main Menu");

        addBook.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMember.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayMembers.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        addBook.addActionListener(e -> addBook());
        addMember.addActionListener(e -> addMember());
        displayBooks.addActionListener(e -> showOutput("Books in Library", library.getBooksList()));
        displayMembers.addActionListener(e -> showOutput("Library Members", library.getMembersList()));
        back.addActionListener(e -> showMainMenu());

        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(addBook);
        panel.add(Box.createVerticalStrut(15));
        panel.add(addMember);
        panel.add(Box.createVerticalStrut(15));
        panel.add(displayBooks);
        panel.add(Box.createVerticalStrut(15));
        panel.add(displayMembers);
        panel.add(Box.createVerticalStrut(30));
        panel.add(back);

        mainPanel.add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ================= Customer Menu =================
    private void showCustomerMenu() {
        mainPanel.removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JLabel title = new JLabel("Customer Menu");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewBooks = new JButton("View Books");
        JButton borrowBook = new JButton("Borrow Book");
        JButton returnBook = new JButton("Return Book");
        JButton searchBook = new JButton("Search Book");
        JButton back = new JButton("Return to Main Menu");

        viewBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
        borrowBook.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnBook.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchBook.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewBooks.addActionListener(e -> showOutput("Books in Library", library.getBooksList()));
        borrowBook.addActionListener(e -> borrowBook());
        returnBook.addActionListener(e -> returnBook());
        searchBook.addActionListener(e -> searchBook());
        back.addActionListener(e -> showMainMenu());

        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(viewBooks);
        panel.add(Box.createVerticalStrut(15));
        panel.add(borrowBook);
        panel.add(Box.createVerticalStrut(15));
        panel.add(returnBook);
        panel.add(Box.createVerticalStrut(15));
        panel.add(searchBook);
        panel.add(Box.createVerticalStrut(30));
        panel.add(back);

        mainPanel.add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ================= Operations =================
    private void addBook() {
        JTextField id = new JTextField();
        JTextField title = new JTextField();
        JTextField author = new JTextField();

        Object[] fields = {
                "Book ID:", id,
                "Title:", title,
                "Author:", author
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            library.addBook(new Book(Integer.parseInt(id.getText()), title.getText(), author.getText()));
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        }
    }

    private void addMember() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();

        Object[] fields = {
                "Member ID:", id,
                "Name:", name
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            library.addMember(new Member(Integer.parseInt(id.getText()), name.getText()));
            JOptionPane.showMessageDialog(this, "Member added successfully!");
        }
    }

    private void borrowBook() {
        JTextField memberId = new JTextField();
        JTextField bookId = new JTextField();

        Object[] fields = {
                "Member ID:", memberId,
                "Book ID:", bookId
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Borrow Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            library.borrowBook(Integer.parseInt(memberId.getText()), Integer.parseInt(bookId.getText()));
        }
    }

    private void returnBook() {
        JTextField memberId = new JTextField();
        JTextField bookId = new JTextField();

        Object[] fields = {
                "Member ID:", memberId,
                "Book ID:", bookId
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Return Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            library.returnBook(Integer.parseInt(memberId.getText()), Integer.parseInt(bookId.getText()));
        }
    }

    private void searchBook() {
        String title = JOptionPane.showInputDialog(this, "Enter Book Title:");
        if (title != null && !title.isEmpty()) {
            // GUI-friendly search
            String result = library.searchBookForGUI(title);
            showOutput("Search Result", result);
        }
    }

    private void showOutput(String title, String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(this, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI().setVisible(true));
    }
}
