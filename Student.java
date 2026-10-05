/**
 * Represents one student who can borrow books.
 */
public class Student {
    private String name;
    private String studentId;

    public Student(String name, String studentId) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.name = name.trim();
        this.studentId = studentId.trim();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return name + " (ID: " + studentId + ")";
    }
}
