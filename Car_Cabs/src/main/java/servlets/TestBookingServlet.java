package servlets;

import Bean.BookingRequest;
import dao.DriverBookingDAO;
import dao.DriverRideHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/TestBookingServlet")
public class TestBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String driverUsername = (String) session.getAttribute("username");

        if (driverUsername != null) {
            System.out.println("Fetching booking data for Driver Dashboard...");
            
            // 1. Fetch available ride requests
            List<BookingRequest> pendingRequests = DriverBookingDAO.getPendingRequests();
            request.setAttribute("pendingRequests", pendingRequests);

            // 2. Fetch completed ride history
            List<BookingRequest> rideHistory = DriverRideHistoryDAO.getDriverRideHistory(driverUsername);
            request.setAttribute("rideHistory", rideHistory);

            // 3. Fetch total earnings for completed rides
            double totalEarnings = DriverRideHistoryDAO.getTotalEarnings(driverUsername);
            request.setAttribute("totalEarnings", totalEarnings);

            // Forward to the Driver Dashboard JSP
            request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
