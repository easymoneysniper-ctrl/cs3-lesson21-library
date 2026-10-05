import java.util.List;
import java.util.Scanner;

/**
 * Menu-driven client for the school library system.
 * Handles user interaction only; library rules live in Library / Book / Loan.
 */
public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("Woodlands High School Library System");
        System.out.println("(Lesson 2.1 — Abstraction Assignment)");

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    addBook(library, input);
                    break;
                case "2":
                    addStudent(library, input);
                    break;
                case "3":
                    library.displayBooks();
                    break;
                case "4":
                    library.displayStudents();
                    break;
                case "5":
                    searchBook(library, input);
                    break;
                case "6":
                    checkout(library, input);
                    break;
                case "7":
                    returnBook(library, input);
                    break;
                case "8":
                    library.displayActiveLoans();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option. Enter 0-8.");
                    break;
            }
            System.out.println();
        }
        input.close();
    }

    private static void printMenu() {
        System.out.println("===== SCHOOL LIBRARY SYSTEM =====");
        System.out.println("1. Add Book");
        System.out.println("2. Add Student");
        System.out.println("3. List Books");
        System.out.println("4. List Students");
        System.out.println("5. Search for Book");
        System.out.println("6. Check Out Book");
        System.out.println("7. Return Book");
        System.out.println("8. View Active Loans");
        System.out.println("0. Exit");
    }

    private static void addBook(Library library, Scanner input) {
        System.out.print("Enter title: ");
        String title = input.nextLine();
        System.out.print("Enter author: ");
        String author = input.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = input.nextLine();
        try {
            Book book = new Book(title, author, isbn);
            library.addBook(book);
            System.out.println("Book added: " + book.getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add book: " + e.getMessage());
        }
    }

    private static void addStudent(Library library, Scanner input) {
        System.out.print("Enter student name: ");
        String name = input.nextLine();
        System.out.print("Enter student ID: ");
        String id = input.nextLine();
        try {
            Student student = new Student(name, id);
            library.addStudent(student);
            System.out.println("Student registered: " + student);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add student: " + e.getMessage());
        }
    }

    private static void searchBook(Library library, Scanner input) {
        System.out.println("Search by: 1) ISBN  2) Title");
        System.out.print("Choose: ");
        String mode = input.nextLine().trim();
        if (mode.equals("1")) {
            System.out.print("Enter ISBN: ");
            String isbn = input.nextLine();
            Book book = library.findBookByIsbn(isbn);
            if (book == null) {
                System.out.println("No book found with that ISBN.");
            } else {
                System.out.println(book);
            }
        } else if (mode.equals("2")) {
            System.out.print("Enter title (or part of title): ");
            String title = input.nextLine();
            List<Book> matches = library.findBooksByTitle(title);
            if (matches.isEmpty()) {
                System.out.println("No books matched that title.");
            } else {
                for (Book book : matches) {
                    System.out.println("  " + book);
                }
            }
        } else {
            System.out.println("Invalid search choice.");
        }
    }

    private static void checkout(Library library, Scanner input) {
        System.out.print("Enter ISBN: ");
        String isbn = input.nextLine();
        System.out.print("Enter student ID: ");
        String studentId = input.nextLine();
        library.checkoutBook(isbn, studentId);
    }

    private static void returnBook(Library library, Scanner input) {
        System.out.print("Enter ISBN: ");
        String isbn = input.nextLine();
        library.returnBook(isbn);
    }
}
