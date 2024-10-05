import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aditya",
                    "root",
                    "aditya009");

            System.out.println("Connection successful!");

            // Close the connection (important!)
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}