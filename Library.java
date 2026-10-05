import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates books, students, and loans for the school library.
 * Checkout/return rules live here as coordination; Book/Loan still protect their own state.
 */
public class Library {
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Loan> loans;

    public Library() {
        books = new ArrayList<Book>();
        students = new ArrayList<Student>();
        loans = new ArrayList<Loan>();
    }

    public void addBook(Book book) {
        if (book == null) {
            return;
        }
        // avoid duplicate ISBN
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.println("A book with that ISBN is already in the library.");
            return;
        }
        books.add(book);
    }

    public void addStudent(Student student) {
        if (student == null) {
            return;
        }
        if (findStudentById(student.getStudentId()) != null) {
            System.out.println("A student with that ID is already registered.");
            return;
        }
        students.add(student);
    }

    public Book findBookByIsbn(String isbn) {
        if (isbn == null) {
            return null;
        }
        String target = isbn.trim();
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(target)) {
                return book;
            }
        }
        return null;
    }

    /** Search by title (case-insensitive partial match). Returns matches. */
    public List<Book> findBooksByTitle(String titleQuery) {
        ArrayList<Book> matches = new ArrayList<Book>();
        if (titleQuery == null || titleQuery.trim().isEmpty()) {
            return matches;
        }
        String q = titleQuery.trim().toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(q)) {
                matches.add(book);
            }
        }
        return matches;
    }

    public Student findStudentById(String studentId) {
        if (studentId == null) {
            return null;
        }
        String target = studentId.trim();
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(target)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Checks out an available book to a registered student.
     * Creates an active Loan when successful.
     */
    public boolean checkoutBook(String isbn, String studentId) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Checkout failed: no book found with that ISBN.");
            return false;
        }
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Checkout failed: no student found with that ID.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Checkout failed: that book is not available.");
            return false;
        }
        // Book protects its own availability flag
        if (!book.checkout()) {
            System.out.println("Checkout failed: that book is not available.");
            return false;
        }
        loans.add(new Loan(book, student));
        System.out.println("Checkout successful.");
        System.out.println(book.getTitle() + " is now checked out to " + student.getName() + ".");
        return true;
    }

    /**
     * Returns a checked-out book: closes the active loan and frees the book.
     */
    public boolean returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Return failed: no book found with that ISBN.");
            return false;
        }
        Loan active = findActiveLoanForBook(isbn);
        if (active == null) {
            System.out.println("Return failed: that book is not currently on loan.");
            return false;
        }
        active.closeLoan();
        book.returnBook();
        System.out.println("Return successful. " + book.getTitle() + " is available again.");
        return true;
    }

    private Loan findActiveLoanForBook(String isbn) {
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getBook().getIsbn().equalsIgnoreCase(isbn.trim())) {
                return loan;
            }
        }
        return null;
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library yet.");
            return;
        }
        System.out.println("--- Books ---");
        for (Book book : books) {
            System.out.println("  " + book);
        }
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        System.out.println("--- Students ---");
        for (Student student : students) {
            System.out.println("  " + student);
        }
    }

    public void displayActiveLoans() {
        boolean any = false;
        System.out.println("--- Active Loans ---");
        for (Loan loan : loans) {
            if (loan.isActive()) {
                System.out.println("  " + loan);
                any = true;
            }
        }
        if (!any) {
            System.out.println("  (none)");
        }
    }
}
