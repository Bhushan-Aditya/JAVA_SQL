import java.sql.*;
import java.util.Scanner;
// aditya bhushan
public class Airplane_Reservation {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement();
             PreparedStatement addBookStmt = conn.prepareStatement("INSERT INTO airline_library (title, author, genre,book_id) VALUES (?, ?, ?,?)");
             PreparedStatement updateBookStmt = conn.prepareStatement("UPDATE airline_library SET author = ?, genre = ? WHERE book_id = ?");
             PreparedStatement deleteBookStmt = conn.prepareStatement("DELETE FROM airline_library WHERE book_id = ?")) {

            Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                System.out.println("\nAirline Library Maintenance Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. Update Book Details");
                System.out.println("3. Delete Book");
                System.out.println("4. View All Books");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter id: ");
                        int book_id = scanner.nextInt();
                        addBookStmt.setString(1, title);
                        addBookStmt.setString(2, author);
                        addBookStmt.setString(3, genre);
                        addBookStmt.setInt(4, book_id);
                        addBookStmt.executeUpdate();
                        System.out.println("Book added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter book ID to update: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter new genre: ");
                        String newGenre = scanner.nextLine();
                        updateBookStmt.setString(1, newAuthor);
                        updateBookStmt.setString(2, newGenre);
                        updateBookStmt.setInt(3, bookId);
                        updateBookStmt.executeUpdate();
                        System.out.println("Book details updated successfully!");
                        break;

                    case 3:
                        System.out.print("Enter book ID to delete: ");
                        int bookIdToDelete = scanner.nextInt();
                        deleteBookStmt.setInt(1, bookIdToDelete);
                        deleteBookStmt.executeUpdate();
                        System.out.println("Book deleted successfully!");
                        break;

                    case 4:
                        ResultSet rs = stmt.executeQuery("SELECT * FROM airline_library");
                        System.out.println("\nAll Books in the Airline Library:");
                        while (rs.next()) {
                            System.out.println("Book ID: " + rs.getInt("book_id") +
                                    ", Title: " + rs.getString("title") +
                                    ", Author: " + rs.getString("author") +
                                    ", Genre: " + rs.getString("genre"));
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}