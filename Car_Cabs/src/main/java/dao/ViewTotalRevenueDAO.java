package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewTotalRevenueDAO {

    /**
     * Retrieves the total revenue by summing up all payments with status 'Paid'.
     *
     * @return The total revenue as a double
     */
    public static double getTotalRevenue() {
        Connection conn = null;
        double totalRevenue = 0.0;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed.");
                return totalRevenue;
            }

            String sql = "SELECT SUM(total_amount) as total FROM bookings WHERE status = 'Confirmed'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalRevenue = rs.getDouble("total");
                System.out.println("Total revenue fetched: " + totalRevenue);
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
        return totalRevenue;
    }
}
