package dao;

import Bean.login;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Validate user login credentials
    public static String validateLogin(String username, String password) {
        Connection conn = DBConnection.getConnection();
        String role = null;

        try {
            String sql = "SELECT role FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
                System.out.println("Login successful. User role: " + role);
            } else {
                System.out.println("Invalid username or password.");
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role; // Returns null if login failed, otherwise returns the user's role
    }
}
