package dao;

import Bean.BookingRequest;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminOrdersDAO {
    /**
     * Retrieves all customer orders from the Bookings table based on status filter.
     * If the status is null or empty, it returns all orders.
     *
     * @param status The status filter (Pending, Confirmed, Completed, Cancelled)
     * @return A list of booking requests matching the filter
     */
    public static List<BookingRequest> getCustomerOrders(String status) {
        List<BookingRequest> orders = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed.");
                return orders;
            }

            String sql = "SELECT * FROM bookings";
            if (status != null && !status.isEmpty() && !status.equals("All")) {
                sql += " WHERE status = ?";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            if (status != null && !status.isEmpty() && !status.equals("All")) {
                stmt.setString(1, status);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookingRequest booking = new BookingRequest();
                booking.setBookingId(rs.getInt("booking_id")); // Updated to bookingId
                booking.setUsername(rs.getString("username"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDropoffLocation(rs.getString("drop_location"));
                booking.setBookingDate(rs.getString("booking_date"));
                booking.setTime(rs.getString("time"));
                booking.setPassengerCount(rs.getInt("passenger_count"));
                booking.setVehicleType(rs.getString("vehicle_type"));
                booking.setTotalAmount(rs.getDouble("total_amount"));
                booking.setStatus(rs.getString("status"));
                orders.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;
    }
}
