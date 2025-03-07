package servlets;

import dao.BookingConfirmationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/BookingConfirmationServlet")
public class BookingConfirmationServlet extends HttpServlet {
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
                resultMessage = BookingConfirmationDAO.acceptBooking(requestId, driverUsername);
            } else if ("reject".equals(action)) {
                resultMessage = BookingConfirmationDAO.rejectBooking(requestId);
            }
            
            if (resultMessage.isEmpty()) {
                System.out.println("Booking action completed successfully.");
            } else {
                System.out.println("Booking action failed: " + resultMessage);
            }
        }

        // Redirect back to the Driver Dashboard
        response.sendRedirect("driverDashboard.jsp");
    }
}
