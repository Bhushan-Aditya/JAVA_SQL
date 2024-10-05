import java.sql.*;

// Aditya Bhushan

public class InsertRecords {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO your_table_name (column1, column2) VALUES (?, ?)")) {

            insertStmt.setString(1, "value1");
            insertStmt.setInt(2, 123);

            int rowsAffected = insertStmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}