import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement();
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO NobelPrize (Name, Year, Subject) VALUES (?, ?, ?)");
             Scanner scanner = new Scanner(System.in)) {

            // Aditya Bhushan [ RA2311003010124 ]

            int numEntries;
            System.out.print("How many Nobel Prize winners do you want to add? ");
            numEntries = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numEntries; i++) {
                System.out.println("\nEnter details for Nobel Prize winner #" + (i + 1) + ":");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Year: ");
                int year = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Subject: ");
                String subject = scanner.nextLine();

                insertStmt.setString(1, name);
                insertStmt.setInt(2, year);
                insertStmt.setString(3, subject);
                insertStmt.executeUpdate();
                System.out.println("Record inserted successfully!");
            }

            // Display all Nobel Prize winners
            ResultSet rs = stmt.executeQuery("SELECT * FROM NobelPrize");
            System.out.println("\nAll Nobel Prize winners:");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("Name") +
                        ", Year: " + rs.getInt("Year") +
                        ", Subject: " + rs.getString("Subject"));
            }

            // Display details of Peace Prize winners
            rs = stmt.executeQuery("SELECT * FROM NobelPrize WHERE Subject = 'Peace'");
            System.out.println("\nNobel Peace Prize winners:");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("Name") +
                        ", Year: " + rs.getInt("Year"));
            }

            // Delete records before 1930
            int deletedRows = stmt.executeUpdate("DELETE FROM NobelPrize WHERE Year < 1930");
            System.out.println("\nDeleted " + deletedRows + " records before 1930.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}