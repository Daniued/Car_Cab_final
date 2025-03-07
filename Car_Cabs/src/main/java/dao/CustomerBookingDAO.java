package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerBookingDAO {

    /**
     * Creates a quick booking in the database with the provided booking details.
     *
     * @param username       The username of the customer
     * @param pickup         The pickup location
     * @param dropoff        The drop-off location
     * @param time           The time of the booking
     * @param passengerCount The number of passengers
     * @param vehicleType    The type of vehicle requested
     * @param price          The calculated price for the ride
     * @return An empty string if successful, or an error message if an error occurs
     */
    public static String createQuickBooking(String username, String pickup, String dropoff, String time,
                                            int passengerCount, String vehicleType, double price) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return "Database connection failed.";
            }

            String sql = "INSERT INTO Bookings (username, pickup_location, drop_location, time, passenger_count, vehicle_type, total_amount, status) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, 'Pending')";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, pickup);
                stmt.setString(3, dropoff);
                stmt.setString(4, time);
                stmt.setInt(5, passengerCount);
                stmt.setString(6, vehicleType);
                stmt.setDouble(7, price);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return ""; // Success
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Booking failed. Please try again.";
    }
}
