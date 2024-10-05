import java.sql.*;

public class Books{

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement();
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO Book (Title, Author, Publication, Price) VALUES (?, ?, ?, ?)");
             CallableStatement increasePriceProc = conn.prepareCall("{CALL IncreaseBookPrice(?)}");
             CallableStatement addNewBookProc = conn.prepareCall("{CALL AddNewBooky(?, ?, ?, ?)}")) {

            // 1. Add new book using PreparedStatement
            insertStmt.setString(1, "HTML, CSS & JavaScript Book");
            insertStmt.setString(2, "Laura Lemay Book");
            insertStmt.setString(3, "Prentice Hall Book");
            insertStmt.setDouble(4, 250.00);
            insertStmt.executeUpdate();

            // 2. Display all book details
            ResultSet rs = stmt.executeQuery("SELECT * FROM Book");
            System.out.println("\nAll Books:");
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title") +
                        ", Author: " + rs.getString("Author") +
                        ", Publication: " + rs.getString("Publication") +
                        ", Price: " + rs.getDouble("Price"));
            }

            // 3. Increase book prices using a stored procedure
            increasePriceProc.setDouble(1, 200.00); // Increase by Rs. 200
            increasePriceProc.execute();
            System.out.println("\nBook prices increased!");

            // 4. Add new record using a stored procedure
            addNewBookProc.setString(1, "New Book Title");
            addNewBookProc.setString(2, "New Author");
            addNewBookProc.setString(3, "New Publication");
            addNewBookProc.setDouble(4, 300.00);
            addNewBookProc.execute();
            System.out.println("\nNew book record added!");


            // 5. You would typically execute Callable Statements for procedures that return
            //    values (e.g., a function or a procedure with OUT parameters) in a similar
            //    way as you execute PreparedStatements.


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}