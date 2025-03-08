package dao;

import Bean.BookingRequest;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverBookingDAO {

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
	            System.out.println("Database connection failed.");
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

	            // Fetch the driver ID using the driver's username
	            String driverIdQuery = "SELECT driver_id, plate FROM Drivers WHERE username = ?";
	            PreparedStatement driverStmt = conn.prepareStatement(driverIdQuery);
	            driverStmt.setString(1, driverUsername);
	            ResultSet driverRs = driverStmt.executeQuery();

	            int driverId = -1;
	            String plateNumber = null;
	            if (driverRs.next()) {
	                driverId = driverRs.getInt("driver_id");
	                plateNumber = driverRs.getString("plate");
	                System.out.println("Driver ID found: " + driverId);
	                System.out.println("Plate Number found: " + plateNumber);
	            } else {
	                System.out.println("No driver found with username: " + driverUsername);
	                return "Driver not found.";
	            }

	            // Fetch the vehicle ID using the plate number from the Vehicles table
	            String vehicleIdQuery = "SELECT vehicle_id FROM Vehicles WHERE plate_number = ?";
	            PreparedStatement vehicleStmt = conn.prepareStatement(vehicleIdQuery);
	            vehicleStmt.setString(1, plateNumber);
	            ResultSet vehicleRs = vehicleStmt.executeQuery();

	            int vehicleId = -1;
	            if (vehicleRs.next()) {
	                vehicleId = vehicleRs.getInt("vehicle_id");
	                System.out.println("Vehicle ID found: " + vehicleId);
	            } else {
	                System.out.println("No vehicle found with plate: " + plateNumber);
	                return "Vehicle not found.";
	            }

	            // Validate the fetched driver ID and vehicle ID
	            if (driverId == -1 || vehicleId == -1) {
	                System.out.println("Invalid driver or vehicle ID: " + driverId + ", " + vehicleId);
	                return "Invalid driver or vehicle ID.";
	            }

	            // Insert into Bookings table with the correct driver ID and vehicle ID
	            String insertSql = "INSERT INTO Bookings (username, driver_id, vehicle_id, pickup_location, drop_location, booking_date, time, passenger_count, vehicle_type, total_amount, status) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Confirmed')";
	            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
	            insertStmt.setString(1, username);
	            insertStmt.setInt(2, driverId);
	            insertStmt.setInt(3, vehicleId); // Include vehicle ID in the booking
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
     * Retrieves all pending ride requests regardless of vehicle type.
     * @return A list of pending ride requests
     */
    public static List<BookingRequest> getPendingRequests() {
        Connection conn = null;
        List<BookingRequest> pendingRequests = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed.");
                return pendingRequests;
            }

            String sql = "SELECT * FROM RequestBooking WHERE status = 'Pending'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookingRequest request = new BookingRequest();
                request.setBookingId(rs.getInt("request_id"));
                request.setUsername(rs.getString("username"));
                request.setPickupLocation(rs.getString("pickup_location"));
                request.setDropoffLocation(rs.getString("drop_location"));
                request.setBookingDate(rs.getString("booking_date"));
                request.setTime(rs.getString("time"));
                request.setPassengerCount(rs.getInt("passenger_count"));
                request.setVehicleType(rs.getString("vehicle_type"));
                request.setTotalAmount(rs.getDouble("total_amount"));
                request.setStatus(rs.getString("status"));

                pendingRequests.add(request);
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
        return pendingRequests;
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
