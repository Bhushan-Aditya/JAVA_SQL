import java.sql.*;
import java.util.Scanner;
// Aditya Bhushan
public class LibraryMaintenance {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement();
             PreparedStatement addBookStmt = conn.prepareStatement("INSERT INTO lib (title, author, book_id) VALUES (?, ?, ?)");
             PreparedStatement updateBookStmt = conn.prepareStatement("UPDATE lib SET author = ? WHERE book_id = ?");
             PreparedStatement deleteBookStmt = conn.prepareStatement("DELETE FROM lib WHERE book_id = ?")) {

            Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                System.out.println("\nLibrary Maintenance Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. Update Book Author");
                System.out.println("3. Delete Book");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter book ID: ");
                        int book_id = scanner.nextInt();
                        addBookStmt.setString(1, title);
                        addBookStmt.setString(2, author);
                        addBookStmt.setInt(3, book_id);
                        addBookStmt.executeUpdate();
                        System.out.println("Book added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter book ID to update: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        updateBookStmt.setString(1, newAuthor);
                        updateBookStmt.setInt(2, bookId);
                        updateBookStmt.executeUpdate();
                        System.out.println("Book author updated successfully!");
                        break;

                    case 3:
                        System.out.print("Enter book ID to delete: ");
                        int bookIdToDelete = scanner.nextInt();
                        deleteBookStmt.setInt(1, bookIdToDelete);
                        deleteBookStmt.executeUpdate();
                        System.out.println("Book deleted successfully!");
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}