package test;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Testbooking {
    public static void main(String[] args) {
        System.out.println("Fetching all data from RequestBooking table...");
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed.");
                return;
            }

            String sql = "SELECT * FROM RequestBooking";
            System.out.println("Executing SQL: " + sql);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String username = rs.getString("username");
                String pickup = rs.getString("pickup_location");
                String dropoff = rs.getString("drop_location");
                String date = rs.getString("booking_date");
                String time = rs.getString("time");
                int passengerCount = rs.getInt("passenger_count");
                String vehicleType = rs.getString("vehicle_type");
                double totalAmount = rs.getDouble("total_amount");
                String status = rs.getString("status");

                System.out.println("Request ID: " + requestId + ", Username: " + username + 
                                   ", Pickup: " + pickup + ", Drop-off: " + dropoff + 
                                   ", Date: " + date + ", Time: " + time + 
                                   ", Passenger Count: " + passengerCount + 
                                   ", Vehicle Type: " + vehicleType + 
                                   ", Total Amount: " + totalAmount + 
                                   ", Status: " + status);
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
        System.out.println("Data fetch operation completed.");
    }
}
