package dao;

import Bean.BookingRequest;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverRideHistoryDAO {

    // Fetch ride history for the driver
	// Fetch ride history for the driver with all statuses except Pending
	public static List<BookingRequest> getDriverRideHistory(String driverUsername) {
	    Connection conn = null;
	    List<BookingRequest> rideHistory = new ArrayList<>();
	    try {
	        conn = DBConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database connection failed.");
	            return rideHistory;
	        }

	        String sql = "SELECT * FROM Bookings WHERE driver_id = (SELECT driver_id FROM Drivers WHERE username = ?) AND status IN ('Confirmed', 'Completed')";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, driverUsername);
	        ResultSet rs = stmt.executeQuery();

	        if (!rs.isBeforeFirst()) { // Check if the result set is empty
	            System.out.println("No ride history found for driver: " + driverUsername);
	        }

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
	            rideHistory.add(booking);
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
	    return rideHistory;
	}


	// Fetch total earnings for the driver
	public static double getTotalEarnings(String driverUsername) {
	    Connection conn = null;
	    double totalEarnings = 0.0;
	    try {
	        conn = DBConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database connection failed.");
	            return totalEarnings;
	        }

	        String sql = "SELECT SUM(total_amount) as total FROM Bookings WHERE driver_id = (SELECT driver_id FROM Drivers WHERE username = ?) "
	        		+ "AND status IN ('Confirmed', 'Completed')";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, driverUsername);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            totalEarnings = rs.getDouble("total");
	            System.out.println("Total earnings for driver " + driverUsername + ": " + totalEarnings);
	        } else {
	            System.out.println("No earnings data found for driver: " + driverUsername);
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
	    return totalEarnings;
	}



    // Fetch total earnings for the driver

}
