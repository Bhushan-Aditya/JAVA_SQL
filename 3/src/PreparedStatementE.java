import java.sql.*;
// Aditya Bhushan
public class PreparedStatementE{
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE major = ?")) {
            stmt.setString(1, "Computer Science");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("student_id") +
                        ", Name: " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}