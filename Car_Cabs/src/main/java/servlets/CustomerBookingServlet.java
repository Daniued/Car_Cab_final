package servlets;

import dao.RequestBookingDAO;
import dao.PricingCalculator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/CustomerBookingServlet")
public class CustomerBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the booking type (quick or planned)
        String bookingType = request.getParameter("bookingType");

        // Get common form data
        String pickup = request.getParameter("pickup");
        String dropoff = request.getParameter("dropoff");
        String vehicleType = request.getParameter("vehicleType");
        String time = request.getParameter("time");
        int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));

        // Get customer username from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp?message=Please log in to continue.");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Calculate price using PricingCalculator
        double price = PricingCalculator.calculatePrice(pickup, dropoff, vehicleType);

        // Handle request booking
        String status = RequestBookingDAO.createRequestBooking(username, pickup, dropoff, time, passengerCount, vehicleType, price);
        if (status.isEmpty()) {
            response.sendRedirect("customerDashboard.jsp?message=Booking request submitted successfully!");
        } else {
            response.getWriter().println("Error in booking request: " + status);
        }
    }
}
