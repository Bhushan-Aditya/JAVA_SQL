import java.sql.*;
import java.util.Scanner;
// Aditya Bhushan
public class LibraryMan {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             PreparedStatement updateStmt = conn.prepareStatement("UPDATE books SET status = ? WHERE book_id = ?")) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter book ID to update: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new status (available/borrowed): ");
            String newStatus = scanner.nextLine();

            updateStmt.setString(1, newStatus);
            updateStmt.setInt(2, bookId);

            int rowsAffected = updateStmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}