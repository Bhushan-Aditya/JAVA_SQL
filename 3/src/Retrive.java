import java.sql.*;
// Aditya Bhushan
public class Retrive{
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("student_id") +
                        ", Name: " + rs.getString("name") +
                        ", Major: " + rs.getString("major"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}