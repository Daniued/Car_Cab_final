package servlets;

import dao.ViewTotalRevenueDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ViewTotalRevenueServlet")
public class ViewTotalRevenueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Fetching total revenue for admin view...");
        double totalRevenue = ViewTotalRevenueDAO.getTotalRevenue();
        request.setAttribute("totalRevenue", totalRevenue);
        request.getRequestDispatcher("viewTotalRevenue.jsp").forward(request, response);
    }
}
