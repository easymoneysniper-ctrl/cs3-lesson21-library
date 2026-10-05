/**
 * Represents the relationship between one student and one borrowed book.
 * Loan status changes only through public behavior (not by outside field writes).
 */
public class Loan {
    private Book book;
    private Student student;
    private boolean active;

    public Loan(Book book, Student student) {
        if (book == null || student == null) {
            throw new IllegalArgumentException("Loan requires a book and a student.");
        }
        this.book = book;
        this.student = student;
        this.active = true;
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isActive() {
        return active;
    }

    /** Closes this loan when the book is returned. */
    public void closeLoan() {
        active = false;
    }

    @Override
    public String toString() {
        String status = active ? "ACTIVE" : "CLOSED";
        return "[" + status + "] " + book.getTitle() + " → " + student.getName()
                + " (ID " + student.getStudentId() + ")";
    }
}
