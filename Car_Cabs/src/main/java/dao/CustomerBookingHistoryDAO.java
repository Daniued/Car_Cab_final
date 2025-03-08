package dao;

import Bean.BookingRequest;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBookingHistoryDAO {

    /**
     * Retrieves all booking history for a specific customer including both confirmed and rejected bookings.
     * @param username The username of the customer
     * @return A list of BookingRequest objects representing the booking history
     */
    public static List<BookingRequest> getBookingHistory(String username) {
        Connection conn = null;
        List<BookingRequest> bookingHistory = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed.");
                return bookingHistory;
            }

            String sql = "SELECT booking_id, pickup_location, drop_location, booking_date, time, vehicle_type, status, total_amount " +
                         "FROM Bookings WHERE username = ? " +
                         "UNION " +
                         "SELECT request_id AS booking_id, pickup_location, drop_location, booking_date, time, vehicle_type, status, total_amount " +
                         "FROM RequestBooking WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookingRequest booking = new BookingRequest();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDropoffLocation(rs.getString("drop_location"));
                booking.setBookingDate(rs.getString("booking_date"));
                booking.setTime(rs.getString("time"));
                booking.setVehicleType(rs.getString("vehicle_type"));
                booking.setStatus(rs.getString("status"));
                booking.setTotalAmount(rs.getDouble("total_amount"));

                bookingHistory.add(booking);
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
        return bookingHistory;
    }
}
