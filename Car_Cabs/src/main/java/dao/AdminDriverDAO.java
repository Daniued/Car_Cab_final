package dao;

import Bean.Driver;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDriverDAO {

    // Method to fetch all driver accounts from the database
	public static List<Driver> getAllDriverAccounts() {
	    List<Driver> driverAccounts = new ArrayList<>();
	    Connection conn = null;

	    try {
	        conn = DBConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database connection failed.");
	            return driverAccounts;
	        }

	        String sql = "SELECT * FROM Drivers";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Driver driver = new Driver();
	            driver.setDriverId(rs.getInt("driver_id"));
	            driver.setUsername(rs.getString("username"));
	            driver.setName(rs.getString("name"));
	            driver.setPhone(rs.getString("phone"));
	            driver.setLicense(rs.getString("license"));
	            driver.setVehicleType(rs.getString("vehicle_type"));
	            driver.setPlate(rs.getString("plate"));
	            System.out.println("Driver found: " + driver.getName()); // Add this line
	            driverAccounts.add(driver);
	        }
	        
	        System.out.println("Total drivers fetched: " + driverAccounts.size());

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
	    return driverAccounts;
	}

}