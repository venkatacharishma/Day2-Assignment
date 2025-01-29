import java.io.*;
import java.util.*;

// Book class
class Book {
    int bookId;
    String title;
    String author;
    int copies;

    Book(int bookId, String title, String author, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }
}

// Member class
class Member {
    int memberId;
    String name;

    Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}

// Transaction class
class Transaction {
    int transactionId;
    int bookId;
    int memberId;
    Date issueDate;
    Date returnDate;

    Transaction(int transactionId, int bookId, int memberId, Date issueDate) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = null;
    }
}

public class LibraryManagementSystem {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();
    private static int transactionCounter = 1;

    public static void main(String[] args) throws IOException {
        loadData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Add Member");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Books");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> updateBook(scanner);
                case 3 -> deleteBook(scanner);
                case 4 -> addMember(scanner);
                case 5 -> issueBook(scanner);
                case 6 -> returnBook(scanner);
                case 7 -> viewBooks();
                case 8 -> {
                    saveData();
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Number of Copies: ");
        int copies = scanner.nextInt();

        books.add(new Book(id, title, author, copies));
        System.out.println("Book added successfully.");
    }

    private static void updateBook(Scanner scanner) {
        System.out.print("Enter Book ID to update: ");
        int id = scanner.nextInt();
        Book book = findBookById(id);
        if (book != null) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter New Title: ");
            book.title = scanner.nextLine();
            System.out.print("Enter New Author: ");
            book.author = scanner.nextLine();
            System.out.print("Enter New Number of Copies: ");
            book.copies = scanner.nextInt();
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void deleteBook(Scanner scanner) {
        System.out.print("Enter Book ID to delete: ");
        int id = scanner.nextInt();
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void addMember(Scanner scanner) {
        System.out.print("Enter Member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        members.add(new Member(id, name));
        System.out.println("Member added successfully.");
    }

    private static void issueBook(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book != null && member != null) {
            if (book.copies > 0) {
                book.copies--;
                transactions.add(new Transaction(transactionCounter++, bookId, memberId, new Date()));
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("No copies available!");
            }
        } else {
            System.out.println("Invalid Book ID or Member ID.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter Transaction ID: ");
        int transactionId = scanner.nextInt();
        for (Transaction transaction : transactions) {
            if (transaction.transactionId == transactionId && transaction.returnDate == null) {
                transaction.returnDate = new Date();
                Book book = findBookById(transaction.bookId);
                if (book != null) book.copies++;
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Transaction not found or book already returned!");
    }

    private static void viewBooks() {
        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.printf("ID: %d, Title: %s, Author: %s, Copies: %d\n",
                    book.bookId, book.title, book.author, book.copies);
        }
    }

    private static Book findBookById(int id) {
        for (Book book : books) {
            if (book.bookId == id) return book;
        }
        return null;
    }

    private static Member findMemberById(int id) {
        for (Member member : members) {
            if (member.memberId == id) return member;
        }
        return null;
    }

    private static void loadData() {
        // Simulate loading data (In real implementation, use file handling)
        System.out.println("Loading data...");
    }

    private static void saveData() {
        // Simulate saving data (In real implementation, use file handling)
        System.out.println("Saving data...");
    }
}