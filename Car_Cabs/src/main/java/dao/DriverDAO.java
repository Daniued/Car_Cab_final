package dao;

import Bean.Driver;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverDAO {

    // Register a new driver with plain string password
    public static String registerDriver(Driver driver) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return "Failed to obtain database connection.";
            }

            conn.setAutoCommit(false);

            // Insert into Users table
            String userSql = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, driver.getUsername());
                userStmt.setString(2, driver.getPassword());
                userStmt.setString(3, "driver");
                if (userStmt.executeUpdate() <= 0) {
                    conn.rollback();
                    return "Failed to insert into Users table.";
                }
            }

            // Insert into Drivers table
            String driverSql = "INSERT INTO Drivers (username, name, phone, license, vehicle_type, plate) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement driverStmt = conn.prepareStatement(driverSql)) {
                driverStmt.setString(1, driver.getUsername());
                driverStmt.setString(2, driver.getName());
                driverStmt.setString(3, driver.getPhone());
                driverStmt.setString(4, driver.getLicense());
                driverStmt.setString(5, driver.getVehicleType());
                driverStmt.setString(6, driver.getPlate());
                if (driverStmt.executeUpdate() <= 0) {
                    conn.rollback();
                    return "Failed to insert into Drivers table.";
                }
            }

            conn.commit();
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return "SQLException: " + e.getMessage();
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
    }
}