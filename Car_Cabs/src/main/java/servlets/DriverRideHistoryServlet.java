package servlets;

import Bean.BookingRequest;
import dao.DriverRideHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/DriverRideHistoryServlet")
public class DriverRideHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String driverUsername = (String) session.getAttribute("username");

        if (driverUsername != null) {
            // Fetch completed ride history and total earnings
            List<BookingRequest> rideHistory = DriverRideHistoryDAO.getDriverRideHistory(driverUsername);
            double totalEarnings = DriverRideHistoryDAO.getTotalEarnings(driverUsername);

            request.setAttribute("rideHistory", rideHistory);
            request.setAttribute("totalEarnings", totalEarnings);

            // Forward to driver dashboard
            request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
