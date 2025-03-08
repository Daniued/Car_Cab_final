package servlets;

import dao.DriverBookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DriverBookingServlet")
public class DriverBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        HttpSession session = request.getSession();
        String driverUsername = (String) session.getAttribute("username");

        if (action != null && driverUsername != null) {
            String resultMessage = "";
            
            if ("accept".equals(action)) {
                System.out.println("Driver " + driverUsername + " is accepting request ID: " + requestId);
                resultMessage = DriverBookingDAO.acceptBooking(requestId, driverUsername);
            } else if ("reject".equals(action)) {
                System.out.println("Driver " + driverUsername + " is rejecting request ID: " + requestId);
                resultMessage = DriverBookingDAO.rejectBooking(requestId);
            }
            
            if (resultMessage.isEmpty()) {
                System.out.println("Action completed successfully.");
            } else {
                System.out.println("Action failed: " + resultMessage);
            }
        }

        // Redirect back to the Driver Dashboard to refresh data
        response.sendRedirect("DriverDashboardServlet");
    }
}
