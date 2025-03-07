package servlets;

import dao.DriverBookingDAO;
import Bean.BookingRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/DriverDashboardServlet")
public class DriverDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Fetching pending bookings for Driver Dashboard...");

        // Fetch all pending booking requests from the database
        List<BookingRequest> pendingRequests = DriverBookingDAO.getPendingRequests();

        // Pass the data to the JSP page
        request.setAttribute("pendingRequests", pendingRequests);
        request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);
    }
}
