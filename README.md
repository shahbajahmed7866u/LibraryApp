# Ahmed Library Management System

This is a simple **Library Management System** written in Java. It supports basic library operations for both staff and customers through a **GUI interface**.

> Note: The GUI is included, but all other functionalities (library operations, data structures, logic) were written manually in Java.  

---

## Features

### Staff
- Add books
- Add members
- View all books
- View all members
- Password-protected login (`lib@786`)

### Customer
- View all books
- Borrow a book
- Return a book
- Search for a book by title

---

## Important Notes
- **No backend/database used**. All data is stored in memory.  
- **Data is not persistent**: once the application is closed, all books, members, and borrowed records are lost.  
- This project is mainly to demonstrate **Java OOP, Collections, and basic GUI** concepts.

---

## Usage
1. Run the `LibraryGUI` class to start the GUI.  
2. Staff must enter the password to access staff operations.  
3. Customers can access library operations directly without a password.

---

## Requirements
- Java 17 or above
- No external dependencies required

---

## Structure
- `library` package contains:
  - `Library.java` → core logic for managing books and members
  - `Book.java` → book data model
  - `Member.java` → member data model
- `LibraryGUI.java` → GUI interface for staff and customer operations

---

## Author
- Code written by **[Your Name]**  
- GUI interface implemented on top of existing logic
