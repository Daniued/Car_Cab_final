package servlets;

import Bean.BookingRequest;
import dao.CustomerBookingHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/CustomerBookingHistoryServlet")
public class CustomerBookingHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp?message=Please login first.");
            return;
        }

        String username = (String) session.getAttribute("username");

        // Fetch booking history from DAO
        List<BookingRequest> bookingHistory = CustomerBookingHistoryDAO.getBookingHistory(username);

        // Set booking history in request attribute and forward to JSP
        request.setAttribute("bookingHistory", bookingHistory);
        request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
    }
}
