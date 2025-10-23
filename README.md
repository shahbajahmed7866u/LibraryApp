# Ahmed Library Management System

Ahmed Library Management System is a Java-based project that simulates a basic library workflow. It provides complete backend logic for managing books and members, borrowing and returning books, and searching by title. A Swing-based GUI (`LibraryGUI.java`) provides an interactive interface for staff and customers. The project uses in-memory storage only (no database).

---

## Features

### Staff
- Add books (ID, title, author)
- Add members (member ID, name)
- View all books
- View all members
- Password-protected staff login (password kept in `internalData/password.txt`)

### Customer
- View available books
- Borrow a book (by member ID + book ID)
- Return a borrowed book (by member ID + book ID)
- Search books by title

---

## Project Structure

project-root/
├─ src/
│ ├─ LibraryGUI.java # GUI (not in a package)
│ └─ library/
│ ├─ Library.java
│ ├─ Book.java
│ ├─ Member.java
│ └─ StaffMenu.java
├─ internalData/
│ └─ password.txt # contains the staff password (e.g. lib@786)
├─ README.md


- `Library.java` contains the core business logic (book/member management, borrow/return, GUI-friendly helpers).  
- `LibraryGUI.java` is the graphical interface and calls methods from the `library` package.  
- `internalData/password.txt` stores the staff password so you can change it without editing code.  

---

## Important Notes

- **No backend / no database**: The application stores all data in memory. When you close the app, all books, members, and borrow records are lost.  
- **Password file**: `internalData/password.txt` must exist in the project root and contain the staff password as plain text (e.g., `lib@786`).  
- **Source files only**: Only `.java` files are included in the repository. Compiled `.class` files should not be uploaded.  
- **Java version**: Works with Java 8+ (recommended Java 11 or later).  

---

## Setup & Run (compile & run from project root)

### 1) Ensure folder layout
Make sure the `internalData/password.txt` file exists (project root, not inside `src`). Example content:

lib@786


### 2) Compile
From the project root run:

mkdir -p out
javac -d out src/library/*.java src/LibraryGUI.java


### 3) Run
From the project root run:

java -cp out LibraryGUI


> If using an IDE (IntelliJ IDEA, Eclipse, VS Code), import the project as a Java project, mark `src` as source folder, and run `LibraryGUI` directly.

---

## Usage

1. Start the app (`LibraryGUI`).  
2. Main screen shows options: **Staff Mode**, **Customer Mode**, **Exit**.  
3. **Staff Mode**: prompts for password. Staff password is read from `internalData/password.txt` (3 attempts allowed). After successful auth, staff can add books/members and view lists.  
4. **Customer Mode**: view books, borrow, return, or search by title.  
5. Closing the app clears all in-memory data (no persistence).  

---

## Recommended `.gitignore`

Add a `.gitignore` to the project root to avoid committing compiled files and IDE config:

*.class
/out/
/bin/
/target/

IDE folders

.vscode/
.idea/
*.iml

OS files

.DS_Store
Thumbs.db


---

## Future Improvements
- Add persistent storage (SQLite, MySQL, or JSON files) for data persistence across runs.  
- Improve GUI with JTable for listing books/members and better forms instead of popups.  
- Add user roles, logging, and audit trail for borrow/return actions.  
- Add input validation and better error messages.  
- Add unit tests for core `Library` logic.  

---

## Troubleshooting

- **Password file not found**: ensure `internalData/password.txt` exists in project root.  
- **Class not found / NoClassDefFound**: ensure you compiled with `-d out` and run with `-cp out`.  
- **GUI layout issues**: adjust the frame size in `LibraryGUI.java` for your screen resolution.  

---

## License

This project is free to use for learning and personal projects. No warranty provided.
