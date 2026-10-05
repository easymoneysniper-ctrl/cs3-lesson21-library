/**
 * Represents one book in the school library.
 * Protects availability so clients cannot mark a book checked out twice.
 */
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        this.title = title.trim();
        this.author = author.trim();
        this.isbn = isbn.trim();
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    /** Marks the book as checked out. Returns false if already on loan. */
    public boolean checkout() {
        if (!available) {
            return false;
        }
        available = false;
        return true;
    }

    /** Marks the book as returned / available again. */
    public void returnBook() {
        available = true;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Checked out";
        return title + " by " + author + " (ISBN: " + isbn + ") — " + status;
    }
}
