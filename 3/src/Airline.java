import java.sql.*;
import java.util.Scanner;

public class Airline {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aditya", "root", "aditya009");
             Statement stmt = conn.createStatement();
             PreparedStatement bookFlightStmt = conn.prepareStatement("INSERT INTO bookings (flight_id, passenger_name) VALUES (?, ?)")) {

            Scanner scanner = new Scanner(System.in);
            ResultSet rs = stmt.executeQuery("SELECT * FROM flights");
            System.out.println("Available Flights:");
            while (rs.next()) {
                System.out.println("Flight ID: " + rs.getInt("flight_id") +
                        ", Destination: " + rs.getString("destination") +
                        ", Departure: " + rs.getTimestamp("departure_time"));
            }
            System.out.print("Enter Flight ID to book: ");
            int flightId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Passenger Name: ");
            String passengerName = scanner.nextLine();

            bookFlightStmt.setInt(1, flightId);
            bookFlightStmt.setString(2, passengerName);
            bookFlightStmt.executeUpdate();

            System.out.println("Flight booked successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}