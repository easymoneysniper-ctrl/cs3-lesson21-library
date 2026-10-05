# Woodlands High School Library System
## CS III — Unit 2 Lesson 2.1 (Abstraction Assignment)

Simon Yepes · Period 3 · October 2026

### What this program does
Menu-driven command-line system for a school librarian to:
- add books and students
- search books (ISBN or title)
- check out / return books
- view active loans

### How to compile and run
```bash
cd java
javac *.java
java LibraryApp
```

### Class responsibilities
| Class | Responsibility |
|-------|----------------|
| **Book** | One library book: title/author/ISBN + availability. Only Book can change its own available flag via `checkout()` / `returnBook()`. |
| **Student** | One borrower: name + student ID. Provides info other classes need. |
| **Loan** | Link between one Book and one Student while a checkout is active. Closed with `closeLoan()`. |
| **Library** | Holds collections of books, students, and loans. Coordinates add/search/checkout/return/display. |
| **LibraryApp** | `main` + Scanner menu. User I/O only — not where business rules live. |

### Design notes (encapsulation)
- All fields are private.
- Client code never does `book.available = false`.
- Duplicate checkout is rejected; invalid ISBN/student ID prints a message and continues.
- Search supports ISBN (exact) and title (partial, case-insensitive).

### Files
- `Book.java`, `Student.java`, `Loan.java`, `Library.java`, `LibraryApp.java`
- `DESIGN.md` — Checkpoint 1 design / UML sketch
- `REFLECTION.md` — final reflection answers
- `test_transcript.txt` — console evidence of testing

### Testing covered
- Normal checkout
- Duplicate checkout (rejected)
- Invalid ISBN
- Invalid student ID
- Return restores availability
- Multiple operations in one run
