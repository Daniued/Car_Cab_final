package dao;

import Bean.Customer;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    // Register a new customer with plain string password
    public static String registerCustomer(Customer customer) {
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
                userStmt.setString(1, customer.getUsername());
                userStmt.setString(2, customer.getPassword());
                userStmt.setString(3, "customer");
                if (userStmt.executeUpdate() <= 0) {
                    conn.rollback();
                    return "Failed to insert into Users table.";
                }
            }

            // Insert into Customers table
            String customerSql = "INSERT INTO Customers (name, address, phone, email, nic) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement custStmt = conn.prepareStatement(customerSql)) {
                custStmt.setString(1, customer.getName());
                custStmt.setString(2, customer.getAddress());
                custStmt.setString(3, customer.getPhone());
                custStmt.setString(4, customer.getEmail());
                custStmt.setString(5, customer.getNic());
                if (custStmt.executeUpdate() <= 0) {
                    conn.rollback();
                    return "Failed to insert into Customers table.";
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
