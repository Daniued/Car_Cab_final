package servlets;

import java.io.IOException;
import dao.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve login parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate login credentials using DAO
        String role = LoginDAO.validateLogin(username, password);

        if (role != null) {
            // Set user session attributes
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            session.setMaxInactiveInterval(30 * 60); // Session timeout in 30 minutes

            // Debugging session
            System.out.println("User logged in: " + username);
            System.out.println("User role: " + role);

            // Redirect based on user role
            switch (role) {
                case "admin":
                    response.sendRedirect("AdminDashboard.jsp");
                    break;
                case "customer":
                    response.sendRedirect("CustomerBookingHistoryServlet");
                    break;

                case "driver":
                    response.sendRedirect("TestBookingServlet");
                    break;
                default:
                    response.getWriter().println("Invalid role. Access denied.");
                    break;
            }
        } else {
            // Invalid credentials handling
            response.sendRedirect("index.jsp?message=Invalid username or password. Please try again.");
        }
    }
}
