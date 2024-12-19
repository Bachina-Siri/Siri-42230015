import java.util.*;

class Book {
    int id;
    String title;
    boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }
}

class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Main {
    private static Map<Integer, Book> books = new HashMap<>();
    private static Map<Integer, Student> students = new HashMap<>();
    private static Map<Integer, Integer> rentals = new HashMap<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add Student");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Available Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> addStudent();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> viewAvailableBooks();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        books.put(id, new Book(id, title));
        System.out.println("Book added successfully!");
    }

    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = scanner.nextInt();
        if (books.containsKey(id)) {
            books.remove(id);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book ID not found!");
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.put(id, new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private static void issueBook() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        if (!students.containsKey(studentId)) {
            System.out.println("Student ID not found!");
            return;
        }
        if (!books.containsKey(bookId)) {
            System.out.println("Book ID not found!");
            return;
        }
        if (books.get(bookId).isIssued) {
            System.out.println("Book is already issued!");
            return;
        }

        books.get(bookId).isIssued = true;
        rentals.put(bookId, studentId);
        System.out.println("Book issued successfully!");
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();

        if (rentals.containsKey(bookId)) {
            books.get(bookId).isIssued = false;
            rentals.remove(bookId);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book ID not found or not issued!");
        }
    }

    private static void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        boolean found = false;
        for (Book book : books.values()) {
            if (!book.isIssued) {
                System.out.println("Book ID: " + book.id + ", Title: " + book.title);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books available.");
        }
    }
}
