# Checkpoint 1 — Design Before Code
Woodlands High School Library System · Lesson 2.1

## Classes and purpose
1. **Book** — one book in the collection  
2. **Student** — one registered borrower  
3. **Loan** — active/closed relationship between a book and a student  
4. **Library** — manages collections and checkout/return workflow  
5. **LibraryApp** — menu / Scanner client (not a domain abstraction)

## State (hidden / private)
**Book (≥3):** `title` (String), `author` (String), `isbn` (String), `available` (boolean)  
**Student (≥2):** `name` (String), `studentId` (String)  
**Loan (≥2):** `book` (Book), `student` (Student), `active` (boolean)  
**Library:** `books` (ArrayList\<Book\>), `students` (ArrayList\<Student\>), `loans` (ArrayList\<Loan\>)

## Behaviors (≥3 where required)
**Book:** `isAvailable()`, `checkout()`, `returnBook()`, getters  
**Loan:** `isActive()`, `closeLoan()`, getters  
**Library:** `addBook`, `addStudent`, `findBookByIsbn`, `findStudentById`, `findBooksByTitle`, `checkoutBook`, `returnBook`, `displayBooks`, `displayStudents`, `displayActiveLoans`

## What does NOT belong
- **Book** should NOT process payroll or store every student in the school.  
- **Student** should NOT change a book's availability flag.  
- **Loan** should NOT own the full catalog of all books.  
- **Library** should NOT parse menu input (that stays in LibraryApp).  
- **LibraryApp** should NOT contain checkout rules (Library + Book + Loan do).

## Simple UML
```
+--------------------+       +--------------------+
|       Book         |       |      Student       |
+--------------------+       +--------------------+
| - title: String    |       | - name: String     |
| - author: String   |       | - studentId: String|
| - isbn: String     |       +--------------------+
| - available: bool  |       | + getName()        |
+--------------------+       | + getStudentId()   |
| + isAvailable()    |       +--------------------+
| + checkout()       |                 ^
| + returnBook()     |                 |
+--------------------+                 |
          ^                            |
          |                            |
          |         +------------------+-----+
          |         |        Loan            |
          |         +------------------------+
          +---------| - book: Book           |
                    | - student: Student     |
                    | - active: boolean      |
                    +------------------------+
                    | + isActive()           |
                    | + closeLoan()          |
                    +------------------------+
                              ^
                              | manages
                    +---------+--------------+
                    |        Library         |
                    +------------------------+
                    | - books: List<Book>    |
                    | - students: List<Student>|
                    | - loans: List<Loan>    |
                    +------------------------+
                    | + addBook()            |
                    | + addStudent()         |
                    | + checkoutBook()       |
                    | + returnBook()         |
                    | + displayBooks()       |
                    | + displayActiveLoans() |
                    +------------------------+
                              ^
                              | uses
                    +---------+--------------+
                    |      LibraryApp        |
                    +------------------------+
                    | + main()               |
                    |   (menu / Scanner)     |
                    +------------------------+
```
