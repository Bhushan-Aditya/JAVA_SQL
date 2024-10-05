import java.sql.*;
// Aditya Bhushan
public class SimpleQuery {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT name, age FROM employees WHERE department = 'Sales'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}