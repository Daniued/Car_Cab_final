package servlets;

import Bean.BookingRequest;
import dao.DriverBookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/TestBookingServlet")
public class TestBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Fetching booking data for display on JSP...");
        
        // Fetch all pending booking requests from the database
        List<BookingRequest> pendingRequests = DriverBookingDAO.getPendingRequests();

        // Pass the data to the JSP page
        request.setAttribute("pendingRequests", pendingRequests);
        request.getRequestDispatcher("testbooking.jsp").forward(request, response);
    }
}
