import java.sql.*;
// Aditya Bhushan
public class ResultSetE {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM bookm");

            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("book_id") +
                        ", Title: " + rs.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}