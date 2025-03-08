package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingConfirmationDAO {

    /**
     * Accepts a booking request by moving it from RequestBooking to Bookings table.
     * Updates the status of the request to 'Accepted' and assigns the driver.
     *
     * @param requestId The ID of the request to accept
     * @param driverUsername The username of the driver accepting the request
     * @return An empty string if successful, or an error message if an error occurs
     */
	public static String acceptBooking(int requestId, String driverUsername) {
	    Connection conn = null;
	    try {
	        conn = DBConnection.getConnection();
	        if (conn == null) {
	            return "Database connection failed.";
	        }

	        conn.setAutoCommit(false);

	        // Get booking request details
	        String selectSql = "SELECT * FROM RequestBooking WHERE request_id = ?";
	        PreparedStatement selectStmt = conn.prepareStatement(selectSql);
	        selectStmt.setInt(1, requestId);
	        ResultSet rs = selectStmt.executeQuery();

	        if (rs.next()) {
	            String username = rs.getString("username");
	            String pickup = rs.getString("pickup_location");
	            String dropoff = rs.getString("drop_location");
	            String date = rs.getString("booking_date");
	            String time = rs.getString("time");
	            int passengerCount = rs.getInt("passenger_count");
	            String vehicleType = rs.getString("vehicle_type");
	            double totalAmount = rs.getDouble("total_amount");

	            // Fetch the driver ID and plate using the username
	            String driverIdQuery = "SELECT driver_id, plate FROM Drivers WHERE username = ?";
	            PreparedStatement driverStmt = conn.prepareStatement(driverIdQuery);
	            driverStmt.setString(1, driverUsername);
	            ResultSet driverRs = driverStmt.executeQuery();

	            int driverId = -1;
	            String plate = "";
	            if (driverRs.next()) {
	                driverId = driverRs.getInt("driver_id");
	                plate = driverRs.getString("plate");
	                System.out.println("Driver ID: " + driverId + ", Plate: " + plate);
	            } else {
	                System.out.println("No driver found with username: " + driverUsername);
	                return "Driver not found.";
	            }

	            // Fetch the vehicle_id using the plate
	         // Fetch the vehicle_id using the plate
	            String vehicleIdQuery = "SELECT vehicle_id FROM Vehicles WHERE plate_number = ?";
	            PreparedStatement vehicleStmt = conn.prepareStatement(vehicleIdQuery);
	            vehicleStmt.setString(1, plate);
	            ResultSet vehicleRs = vehicleStmt.executeQuery();

	            int vehicleId = -1;
	            if (vehicleRs.next()) {
	                vehicleId = vehicleRs.getInt("vehicle_id");
	                System.out.println("Vehicle ID found: " + vehicleId);
	            } else {
	                System.out.println("No vehicle found with plate: " + plate);
	                
	                // Extra debugging: List all available plates in the database
	                String debugQuery = "SELECT plate_number FROM Vehicles";
	                PreparedStatement debugStmt = conn.prepareStatement(debugQuery);
	                ResultSet debugRs = debugStmt.executeQuery();
	                
	                System.out.println("Available plates in the database:");
	                while (debugRs.next()) {
	                    System.out.println(" - " + debugRs.getString("plate_number"));
	                }
	                
	                return "Vehicle not found.";
	            }


	            // Insert into Bookings table with the correct driver ID and vehicle ID
	            String insertSql = "INSERT INTO Bookings (username, driver_id, vehicle_id, pickup_location, drop_location, booking_date, time, passenger_count, vehicle_type, total_amount, status) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Confirmed')";
	            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
	            insertStmt.setString(1, username);
	            insertStmt.setInt(2, driverId);
	            insertStmt.setInt(3, vehicleId);
	            insertStmt.setString(4, pickup);
	            insertStmt.setString(5, dropoff);
	            insertStmt.setString(6, date);
	            insertStmt.setString(7, time);
	            insertStmt.setInt(8, passengerCount);
	            insertStmt.setString(9, vehicleType);
	            insertStmt.setDouble(10, totalAmount);

	            int rowsInserted = insertStmt.executeUpdate();

	            if (rowsInserted > 0) {
	                System.out.println("Booking inserted successfully into Bookings table.");

	                // Update request status to Accepted
	                String updateSql = "UPDATE RequestBooking SET status = 'Accepted' WHERE request_id = ?";
	                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
	                updateStmt.setInt(1, requestId);
	                updateStmt.executeUpdate();

	                conn.commit();
	                return ""; // Success
	            } else {
	                System.out.println("Failed to insert booking into Bookings table.");
	            }
	        } else {
	            System.out.println("No matching request found for Request ID: " + requestId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(true);
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return "Failed to accept booking.";
	}

    /**
     * Rejects a booking request by updating its status to 'Cancelled' in the RequestBooking table.
     *
     * @param requestId The ID of the request to reject
     * @return An empty string if successful, or an error message if an error occurs
     */
    public static String rejectBooking(int requestId) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return "Database connection failed.";
            }

            String sql = "UPDATE RequestBooking SET status = 'Cancelled' WHERE request_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, requestId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return ""; // Success
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
        return "Failed to reject booking.";
    }
}
